package framework.aurora.db.tools;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public class MakeUrlDb {
	
	
	public static String geturldb(DataBaseConfigurationConnectionParameter conf,  String serviceName) {
		
		StringBuffer url = new StringBuffer();
		
		if(DataBaseEnum.ORACLE.equals(conf.getDataBase())) {
			return "jdbc:oracle:thin:@"+conf.getHost()+":"+conf.getPort()+":"+serviceName;	
		}else {
		url.append(conf.getDataBase().getDataBase())
		.append("://")
		.append(conf.getHost())
		.append(":")
		.append(conf.getPort())
		.append("/")
		.append(conf.getDataBaseName());
		}
		
		if(DataBaseEnum.MY_SQL.equals(conf.getDataBase()))
		url.append("?autoReconnect=true&useSSL=true");
		
		return url.toString();
	}

}
