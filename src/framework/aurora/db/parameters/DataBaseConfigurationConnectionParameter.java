package framework.aurora.db.parameters;

public class DataBaseConfigurationConnectionParameter {
	public String host;
	public String port;
	public String dataBaseName;
	public String user;
	public String password;

	public DataBaseConfigurationConnectionParameter(String host, String port, String dataBaseName, String user,
			String password) {
		this.host = host;
		this.port = port;
		this.dataBaseName = dataBaseName;
		this.user = user;
		this.password = password;
	}
}