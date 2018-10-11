package framework.aurora.db.tools;

public enum DataBaseEnum {
	
	MY_SQL("jdbc:mysql"),
    POSTGRES("jdbc:postgresql");
 
    private final String dataBase;
 
    DataBaseEnum(String dataBase) {
        this.dataBase = dataBase;
    }
 
    public String getDataBase() {
        return dataBase;
    }

}
