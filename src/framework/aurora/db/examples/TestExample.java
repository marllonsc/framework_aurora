package framework.aurora.db.examples;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class TestExample {

	public static void main(String[] args) {
//		BaseDao<Student> dao = new BaseDao<Student>(DataBaseEnum.MY_SQL,null,Student.class);
		
//		boolean r = dao.insertObject(new Student(null, "Daniel", new Date()));
		
//		System.out.println(r);
		
//		java.util.List<Student> stu = dao.returnAll();
		
//		System.out.println(stu);
		
		
		DefeitoDAO dao = new DefeitoDAO();
		
		try {
			List<Defeitos> defs = dao.getDefeitos();
			System.out.println(defs);
			for (Defeitos defeitos : defs) {
				System.out.println(defeitos.getEquipe());
			}
		} catch (SQLException e) {
			System.out.println("Connection error!!");
		}
		
	}

}
