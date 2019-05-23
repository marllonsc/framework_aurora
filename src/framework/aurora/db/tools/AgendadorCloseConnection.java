package framework.aurora.db.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;

public class AgendadorCloseConnection extends TimerTask {

	private Connection con;

	public AgendadorCloseConnection(Connection con) {
		this.con = con;
	}

	@Override
	public void run() {
		try {
//			System.out.println("waiting to close!");
			Thread.sleep(30000);
			this.con.close();
			System.out.println("General Connection Close! - " +  new Date());
		} catch (SQLException | InterruptedException | NullPointerException e ) {
			System.out.println("Not Possible close connection right now!  - " +  new Date());
		}finally {
			this.cancel();
		}
	}

}