package framework.aurora.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;
import framework.aurora.db.tools.AgendadorCloseConnection;
import framework.aurora.db.tools.CloseConnectionSearch;
import framework.aurora.db.tools.DataBaseEnum;
import framework.aurora.db.tools.MakeUrlDb;

public abstract class DataBaseConfigurationConnection {

	private static Connection con;

	private boolean checkconnection;

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
		} catch (Exception e) {
			this.checkconnection = false;
			System.out.println("Database Connection Error!");
		} finally {
			Timer timer = new Timer();
			AgendadorCloseConnection agendador = new AgendadorCloseConnection(con);
			timer.schedule(agendador, 0, parameterObject.getCloseConnection());
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
			st.close();
			con.close();
		} catch (Exception e) {
			result = false;
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
			return rs;
		} catch (Exception e) {
			return null;
		} finally {
			Timer timer = new Timer();
			CloseConnectionSearch agendador = new CloseConnectionSearch(st, rs);
			timer.schedule(agendador, 0, parameterObject.getCloseConnection());
		}

	}

	protected boolean setComitTrue() {
		try {
			con.setAutoCommit(true);
			con.commit();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	protected boolean setComitFalse() {
		try {
			con.setAutoCommit(false);
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	protected boolean checkConnection() {
		return checkconnection;
	}

}
