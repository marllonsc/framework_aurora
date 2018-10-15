package framework.aurora.db.examples;

import framework.aurora.db.persistence.BaseDao;
import framework.aurora.db.tools.DataBaseEnum;

public class TestExample {

	public static void main(String[] args) {
		BaseDao<Student> dao = new BaseDao<Student>(DataBaseEnum.MY_SQL, Student.class);
		
//		boolean r = dao.insertObject(new Student(null, "Daniel", new Date()));
		
//		System.out.println(r);
		
		java.util.List<Student> stu = dao.returnAll();
		
		System.out.println(stu);
		
	}

}
