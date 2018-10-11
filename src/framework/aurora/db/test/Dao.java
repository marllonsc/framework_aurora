package framework.aurora.db.test;

import framework.aurora.db.persistence.BaseDao;
import framework.aurora.db.tools.DataBaseEnum;

public class Dao extends BaseDao{

	
	public Dao() {
		super(Student.class, DataBaseEnum.MY_SQL);
	}

}
