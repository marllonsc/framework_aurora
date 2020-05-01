package framework.aurora.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;
import framework.aurora.db.tools.*;

public abstract class DataBaseConfigurationConnection {

	private static LoggerHelper loggerHelper = new LoggerHelper(DataBaseConfigurationConnection.class.getName());

	protected static Connection con;

	protected boolean checkconnection;

	public DataBaseConfigurationConnection(DataBaseConfigurationConnectionParameter parameterObject) {
		createConnection(parameterObject);
	}

	private void createConnection(DataBaseConfigurationConnectionParameter parameterObject) {
		String porta = parameterObject.getPort();
		String local = parameterObject.getHost();

		if (local == null) {
			local = "localhost";
			parameterObject.setHost("localhost");
		}

		if (porta == null) {
			parameterObject.setPort(putPort(parameterObject));
		}

		try {

			Class.forName(putDriver(parameterObject)).newInstance();

			con = DriverManager.getConnection(MakeUrlDb.geturldb(parameterObject), parameterObject.getUser(),
					parameterObject.getPassword());
			this.checkconnection = true;
			loggerHelper.info("Database Connection Success!");
		} catch (Exception e) {
			this.checkconnection = false;
			loggerHelper.error("Database Connection Error!");
		} finally {

		}
	}

	private String putDriver(DataBaseConfigurationConnectionParameter parameterObject) {

		if (DataBaseEnum.MY_SQL.equals(parameterObject.getDataBase())) {
			return "com.mysql.cj.jdbc.Driver";
		} else if (DataBaseEnum.ORACLE.equals(parameterObject.getDataBase())) {
			return "oracle.jdbc.driver.OracleDriver";
		} else if (DataBaseEnum.POSTGRES.equals(parameterObject.getDataBase())) {
			return "org.postgresql.Driver";
		}

		return null;
	}

	private String putPort(DataBaseConfigurationConnectionParameter parameterObject) {

		if (DataBaseEnum.MY_SQL.equals(parameterObject.getDataBase())) {
			return "3306";
		} else if (DataBaseEnum.ORACLE.equals(parameterObject.getDataBase())) {
			return "1521";
		} else if (DataBaseEnum.POSTGRES.equals(parameterObject.getDataBase())) {
			return "5432";
		}

		return null;

	}

	protected boolean executeSQL(String sql, DataBaseConfigurationConnectionParameter parameterObject) {
		boolean result = true;
		try {
			if (!checkConnection()) {
				createConnection(parameterObject);
			}
			Statement st = con.createStatement();
			st.execute(sql);
			loggerHelper.info("SQL - " + sql);
			st.close();
			con.close();
			this.checkconnection = false;
		} catch (Exception e) {
			result = false;
			loggerHelper.error(e.getMessage());
		}
		return result;
	}

	protected ResultSet executeSearchSQL(String sql, DataBaseConfigurationConnectionParameter parameterObject) {
		Statement st = null;
		ResultSet rs = null;
		try {
			if (!checkConnection()) {
				createConnection(parameterObject);
			}
			st = con.createStatement();
			rs = st.executeQuery(sql);
			loggerHelper.info("SQL - " + sql);
			return rs;
		} catch (Exception e) {
			loggerHelper.error(e.getMessage());
			return null;
		} finally {

		}

	}

	protected boolean setComitTrue() {
		try {
			con.setAutoCommit(true);
			loggerHelper.info("Set commit True!");
			con.commit();
			return true;
		} catch (SQLException e) {
			loggerHelper.error(e.getMessage());
			return false;
		}

	}

	protected boolean setComitFalse() {
		try {
			con.setAutoCommit(false);
			loggerHelper.info("Set commit false!");
			return true;
		} catch (SQLException e) {
			loggerHelper.error(e.getMessage());
			return false;
		}

	}

	protected boolean checkConnection() {
		return checkconnection;
	}

}
