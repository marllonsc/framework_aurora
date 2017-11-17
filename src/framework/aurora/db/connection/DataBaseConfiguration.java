package framework.aurora.db.connection;

import java.sql.ResultSet;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public abstract class DataBaseConfiguration extends DataBaseConfigurationConnection{
	
	public DataBaseConfiguration(String host, String port, String dataBaseName,
			String user, String password) {
		super(new DataBaseConfigurationConnectionParameter(host, port, dataBaseName, user, password));
	}

	protected boolean executeSql(String sql){
        return super.executeSQL(sql);
    }
    
    protected ResultSet executeSearchSql(String sql){
        return super.executeSearchSQL(sql);
    }
}
