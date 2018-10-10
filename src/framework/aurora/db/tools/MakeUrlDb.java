package framework.aurora.db.tools;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public class MakeUrlDb {
	
	
	public static String geturldb(DataBaseConfigurationConnectionParameter conf) {
		
		StringBuffer url = new StringBuffer();
		url.append(conf.getDataBase())
		.append("//")
		.append(conf.getHost())
		.append(":")
		.append(conf.getPort())
		.append("/")
		.append(conf.getDataBaseName());
		
		return url.toString();
	}

}
