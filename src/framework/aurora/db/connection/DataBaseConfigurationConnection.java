package framework.aurora.db.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import framework.aurora.db.parameters.DataBaseConfigurationConnectionParameter;

public abstract class DataBaseConfigurationConnection {

	
	private Connection con;

	public DataBaseConfigurationConnection(DataBaseConfigurationConnectionParameter parameterObject){
		
		String porta = parameterObject.port;
		String local = parameterObject.host;
		if(local == null){
			local = "localhost";
		}
		
		if(porta == null){
			porta = "3306";
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://"+local+":"+porta+"/"+parameterObject.dataBaseName, parameterObject.user , parameterObject.password);
		}catch(Exception e){
			System.out.println("Não foi possivel conectar");
		}
	}
	
	
	protected boolean executeSQL(String sql){
		boolean result = true;
		 try{
	
			Statement st = con.createStatement();
			st.execute(sql);
			st.close();
		 }
		 catch(Exception e) {
			 result = false;
		 }
		 return result;
	}

	
	protected ResultSet executeSearchSQL(String sql){
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		 }
		 catch(Exception e) {
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