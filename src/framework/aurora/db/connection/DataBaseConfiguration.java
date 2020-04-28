package framework.aurora.db.connection;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;
import framework.aurora.db.tools.XmlReader;

public abstract class DataBaseConfiguration extends DataBaseConfigurationConnection {

	private static String path = "./src/DbConfiguration/dbInfoConexao.properties";

	public DataBaseConfiguration() {
		super(loadingConfiguration());
	}

	protected boolean executeSQL(String sql) {
		return super.executeSQL(sql, reconnect());
	}

	protected ResultSet executeSearchSQL(String sql) {
		return super.executeSearchSQL(sql, reconnect());
	}

	private static DataBaseConfigurationConnectionParameter loadingConfiguration() {
		return readConfiguration();
	}

	private static DataBaseConfigurationConnectionParameter readConfiguration() {

		if (!checkFileExist(path)) {
			searchDirectory(new File("../"), "dbInfoConexao.xml");
		}
		
		try {
			return XmlReader.returnConfurationXML(path);
		} catch (Exception e) {
			System.out.println("Configuration not found!");
			e.printStackTrace();
			return new DataBaseConfigurationConnectionParameter();
		}
		

//		try {
//			FileReader gravador = new FileReader(path);
//			BufferedReader entrada = new BufferedReader(gravador);
//			List<String> valuesConf = new ArrayList<>();
//
//			String linha;
//			while ((linha = entrada.readLine()) != null) {
//				valuesConf.add(linha);
//			}
//			System.out.println("Configuration found!");
//			entrada.close();
//			return valuesConf;
//		} catch (IOException e) {
//			System.out.println("Configuration not found!");
//			return null;
//		}

	}

	private static boolean checkFileExist(String path) {
		File file = new File(path);
		return file.exists();
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
//		  System.out.println("Searching directory ... " + file.getAbsoluteFile());
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

	private DataBaseConfigurationConnectionParameter reconnect() {
		if (!checkConnection()) {
			return loadingConfiguration();
		}
		return new DataBaseConfigurationConnectionParameter();
	}

}
