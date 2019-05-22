package framework.aurora.db.tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public class LeituraXML {
	
	
	public static DataBaseConfigurationConnectionParameter returnConfurationXML(String file) throws Exception{
		File fXmlFile = new File("arquivo.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		
		String dataBase = doc.getElementsByTagName("DATABASE").item(0).getTextContent();
		String host = doc.getElementsByTagName("HOST").item(0).getTextContent();
		String port = doc.getElementsByTagName("PORT").item(0).getTextContent();
		String dataBaseName = doc.getElementsByTagName("DATABASENAME").item(0).getTextContent();
		String user = doc.getElementsByTagName("USER").item(0).getTextContent();
		String password = doc.getElementsByTagName("PASSWORD").item(0).getTextContent();
		
		
		DataBaseConfigurationConnectionParameter configuration = new DataBaseConfigurationConnectionParameter(dataBase, host, port, dataBaseName, user, password);
		
		return configuration;
		
	}

}