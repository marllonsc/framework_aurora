package framework.aurora.db.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		this.cancel();	
	}

}
