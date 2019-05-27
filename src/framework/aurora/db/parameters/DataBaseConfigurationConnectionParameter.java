package framework.aurora.db.parameters;

import framework.aurora.db.tools.DataBaseEnum;

public class DataBaseConfigurationConnectionParameter {

	private DataBaseEnum dataBase;
	private String host;
	private String port;
	private String dataBaseName;
	private String user;
	private String password;
	private Integer closeConnection;

	public DataBaseConfigurationConnectionParameter() {
	}

	public DataBaseConfigurationConnectionParameter(String dataBase, String host, String port, String dataBaseName,
			String user, String password, Integer closeConnection) {
		setDataBaseEnum(dataBase);
		this.host = host;
		this.port = port;
		this.dataBaseName = dataBaseName;
		this.user = user;
		this.password = password;
		this.closeConnection = closeConnection;
	}

	private void setDataBaseEnum(String dataBase) {
		if ("MY_SQL".equalsIgnoreCase(dataBase)) {
			this.dataBase = DataBaseEnum.MY_SQL;
		} else if ("ORACLE".equalsIgnoreCase(dataBase)) {
			this.dataBase = DataBaseEnum.ORACLE;
		} else if ("POSTGRES".equalsIgnoreCase(dataBase)) {
			this.dataBase = DataBaseEnum.POSTGRES;
		}

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DataBaseEnum getDataBase() {
		return dataBase;
	}

	public void setDataBase(DataBaseEnum dataBase) {
		this.dataBase = dataBase;
	}

	public Integer getCloseConnection() {
		return closeConnection;
	}

	public void setCloseConnection(Integer closeConnection) {
		this.closeConnection = closeConnection;
	}

}