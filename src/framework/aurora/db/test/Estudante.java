package framework.aurora.db.test;

public class Estudante{

	private Integer Id_x;
	private Integer Name_y;

	

	public Estudante(Integer id, Integer name) {
		super();
		this.Id_x = id;
		this.Name_y = name;
		
	}
	
	
	public Estudante(){
		
	}

	public Integer getId_x() {
		return Id_x;
	}

	public void setId_x(Integer id) {
		this.Id_x = id;
	}

	public Integer getName_y() {
		return Name_y;
	}

	public void setName_y(Integer name) {
		this.Name_y = name;
	}

	
	
	
	
}
