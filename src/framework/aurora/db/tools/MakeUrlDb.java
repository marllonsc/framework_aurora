package framework.aurora.db.tools;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public class MakeUrlDb {
	
	
	public static String geturldb(DataBaseConfigurationConnectionParameter conf) {
		
		StringBuffer url = new StringBuffer();
		url.append(conf.getDataBase().getDataBase())
		.append("://")
		.append(conf.getHost())
		.append(":")
		.append(conf.getPort())
		.append("/")
		.append(conf.getDataBaseName())
		.append("?autoReconnect=true&useSSL=true");
		
		return url.toString();
	}

}
