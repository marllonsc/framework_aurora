package framework.aurora.db.test;

public class maintest {

	public static void main(String[] args) {
		
		Dao dao = new Dao();
		
		dao.insertObject(new estudante(null, "Marllon", null, "msc@gmail.com"));

	}

}
