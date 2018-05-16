package framework.aurora.db.connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public abstract class DataBaseConfiguration extends DataBaseConfigurationConnection {

	private static String path = "./DbConfiguration/connectionInformation.txt";

	public DataBaseConfiguration() {
		super(loadingConfiguration());
	}

	protected boolean executeSql(String sql) {
		return super.executeSQL(sql);
	}

	protected ResultSet executeSearchSql(String sql) {
		return super.executeSearchSQL(sql);
	}

	private static List<String> readConfiguration() {

		try {
			FileReader gravador = new FileReader(path);
			BufferedReader entrada = new BufferedReader(gravador);
			List<String> valuesConf = new ArrayList<>();
			
			String linha;
			while ((linha = entrada.readLine()) != null) {
				valuesConf.add(linha);
			}
			System.out.println("Configuration found!");
			entrada.close();
			return valuesConf;
		} catch (IOException e) {
			System.out.println("Configuration not found!");
			return null;
		}

	}
	
	private static DataBaseConfigurationConnectionParameter loadingConfiguration() {
		List<String> valuesConf = readConfiguration();
		if(valuesConf!= null && valuesConf.size() == 5) {
			new DataBaseConfigurationConnectionParameter(valuesConf.get(0), valuesConf.get(1), valuesConf.get(2), valuesConf.get(3), valuesConf.get(4));
		}else {
			return new DataBaseConfigurationConnectionParameter();
		}
		return new DataBaseConfigurationConnectionParameter();
	}
}
