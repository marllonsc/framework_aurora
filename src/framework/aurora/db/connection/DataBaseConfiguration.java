package framework.aurora.db.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;
import framework.aurora.db.tools.DataBaseEnum;

public abstract class DataBaseConfiguration extends DataBaseConfigurationConnection {
	

	private static String path = "./src/DbConfiguration/dbInfoConexao.properties";

	public DataBaseConfiguration(DataBaseEnum dataBase, String serviceNameOracle) {
		super(loadingConfiguration(dataBase), serviceNameOracle);
	}

	protected boolean executeSql(String sql) {
		return super.executeSQL(sql);
	}

	protected ResultSet executeSearchSql(String sql) {
		return super.executeSearchSQL(sql);
	}

	private static List<String> readConfiguration() {
		
		if(!checkFileExist(path)) {
		searchDirectory(new File("../"), "dbInfoConexao.properties");
		}

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
	
	private static boolean checkFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	private static  DataBaseConfigurationConnectionParameter loadingConfiguration(DataBaseEnum dataBase) {
		List<String> valuesConf = readConfiguration();
		if(valuesConf!= null && valuesConf.size() == 5) {
			return new DataBaseConfigurationConnectionParameter(dataBase,valuesConf.get(0), valuesConf.get(1), valuesConf.get(2), valuesConf.get(3), valuesConf.get(4));
		}else {
			return new DataBaseConfigurationConnectionParameter();
		}
	}
	
	private static void searchDirectory(File directory, String fileNameToSearch) {


		if (directory.isDirectory()) {
		    search(directory, fileNameToSearch);
		} else {
		    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}

	  }
	
	private static List<String> search(File file, String filename) {

		if (file.isDirectory()) {
		  System.out.println("Searching directory ... " + file.getAbsoluteFile());
		  List<String> result = new ArrayList<String>();
			
		    if (file.canRead()) {
			for (File temp : file.listFiles()) {
			    if (temp.isDirectory()) {
				search(temp, filename);
			    } else {
				if (filename.equals(temp.getName())) {			
				    result.add(temp.getAbsoluteFile().toString());
				    path = temp.getAbsoluteFile().toString();
				    return result;
			    }

			}
		    }

		 } else {
			System.out.println(file.getAbsoluteFile() + "Permission Denied");
		 }
		}
		return null;
	}
	
	
}
