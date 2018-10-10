package framework.aurora.db.test;

import java.util.Date;

public class maintest {

	public static void main(String[] args) {
		
		Dao dao = new Dao();
		
		dao.insertObject(new Student(null, "Marllon", new Date()));

	}

}
