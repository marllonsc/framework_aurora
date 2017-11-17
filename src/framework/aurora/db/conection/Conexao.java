package framework.aurora.db.conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Conexao {

	
	private Connection con;

	public Conexao(String local, String porta, String nomeBD ,String usuario, String password){
		
		if(local == null){
			local = "localhost";
		}
		
		if(porta == null){
			porta = "3306";
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://"+local+":"+porta+"/"+nomeBD, usuario , password);
		}catch(Exception e){
			System.out.println("Não foi possivel conectar");
		}
	}
	
	
	protected boolean executarSQL(String sql){
		boolean result = true;
		 try{
	
			Statement st = con.createStatement();
			st.execute(sql);
			st.close();
		 }
		 catch(Exception e) {
			 System.out.println("Nao foi possivel executar SQL.");
			 result = false;
		 }
		 return result;
	}

	
	protected ResultSet executarBuscaSQL(String sql){
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		 }
		 catch(Exception e) {
			System.out.println("Nao foi possível recuperar dados.");
			return null;
		 }
		
	}
	
	protected void setComitTrue() {
		try {
			con.setAutoCommit(true);
			con.commit();
		} catch (SQLException e) {
			System.out.println("Erro ao setar verdadeiro no commit");
		}
	
	}
	
	protected void setComitFalse() {
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Erro ao setar verdadeiro no commit");
		}
	
	} 
	
}
