package framework.aurora.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;
import framework.aurora.db.tools.DataBaseEnum;
import framework.aurora.db.tools.MakeUrlDb;

public abstract class DataBaseConfigurationConnection {

	private Connection con;

	public DataBaseConfigurationConnection(DataBaseConfigurationConnectionParameter parameterObject) {

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
		} catch (Exception e) {
			System.out.println("Não foi possivel conectar");
		}
	}

	private String putDriver(DataBaseConfigurationConnectionParameter parameterObject) {
		
		if (DataBaseEnum.MY_SQL.equals(parameterObject.getDataBase())) {
			return "com.mysql.cj.jdbc.Driver";
		} else if (DataBaseEnum.POSTGRES.equals(parameterObject.getDataBase())) {
			return "org.postgresql.Driver";
		}
		
		return null;
	}


	private String putPort(DataBaseConfigurationConnectionParameter parameterObject) {

		if (DataBaseEnum.MY_SQL.equals(parameterObject.getDataBase())) {
			return "3306";
		} else if (DataBaseEnum.POSTGRES.equals(parameterObject.getDataBase())) {
			return "5432";
		}
		
		return null;

	}

	protected boolean executeSQL(String sql) {
		boolean result = true;
		try {

			Statement st = con.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	protected ResultSet executeSearchSQL(String sql) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			return null;
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

}
