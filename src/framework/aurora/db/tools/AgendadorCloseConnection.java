package framework.aurora.db.tools;

import java.sql.Connection;
import java.sql.SQLException;
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
			this.cancel();
//			System.out.println("Connection Closed!");
		} catch (SQLException | InterruptedException e) {
			System.out.println("Not Possible close connection right now!");
		}
	}

}