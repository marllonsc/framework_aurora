package framework.aurora.db.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TimerTask;

public class CloseConnectionSearch extends TimerTask{
	
	private ResultSet rs;
	private Statement st;
	
	public CloseConnectionSearch(Statement st, ResultSet rs) {
		this.st = st;
		this.rs = rs;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(30000);
			this.st.close();
			this.rs.close();
			System.out.println("Connection Search Close! - " +  new Date());
		} catch (SQLException | InterruptedException | NullPointerException e) {
			e.printStackTrace();
			System.out.println("Not Possible close connection right now! - " +  new Date());
		}finally {
			this.cancel();
		}
		
	}

}
