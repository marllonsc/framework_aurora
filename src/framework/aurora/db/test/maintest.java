package framework.aurora.db.test;

import java.awt.List;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class maintest {

	public static void main(String[] args) {
		
		Dao dao = new Dao();
		
//		dao.insertObject(new Student(null, "Marllon", new Date()));
		java.util.List<Object> st =  dao.returnObjects(new Student());
		for (Object object : st) {
			System.out.println(((Student) object).getBirthday());		}

	}

}
