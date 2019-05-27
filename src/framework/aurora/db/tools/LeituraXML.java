package framework.aurora.db.tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public class LeituraXML {

	public static DataBaseConfigurationConnectionParameter returnConfurationXML(String file) throws Exception {
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		String dataBase = doc.getElementsByTagName("DATABASE").item(0).getTextContent();
		String host = doc.getElementsByTagName("HOST").item(0).getTextContent();
		String port = doc.getElementsByTagName("PORT").item(0).getTextContent();
		String dataBaseName = doc.getElementsByTagName("DATABASENAME").item(0).getTextContent();
		String user = doc.getElementsByTagName("USER").item(0).getTextContent();
		String password = doc.getElementsByTagName("PASSWORD").item(0).getTextContent();
		Integer closeConnection = getTime(doc);

		DataBaseConfigurationConnectionParameter configuration = new DataBaseConfigurationConnectionParameter(dataBase,
				host, port, dataBaseName, user, password, closeConnection);

		return configuration;

	}

	private static Integer getTime(Document doc) {
		Integer closeConnection = 90000;
		try {
			closeConnection = (Integer.parseInt(doc.getElementsByTagName("CLOSECONNECTION").item(0).getTextContent())
					* 1000);
		} catch (Exception e) {
			closeConnection = 90000;
		}
		return closeConnection;
	}

}
