package framework.aurora.db.test;

public class BandaCD {
	
	private Integer Banda_id;
	private Integer Cd_id;
	
	public BandaCD() {}
	
	public BandaCD(Integer banda_id, Integer cd_id) {
		super();
		Banda_id = banda_id;
		Cd_id = cd_id;
	}

	public Integer getBanda_id() {
		return Banda_id;
	}

	public void setBanda_id(Integer banda_id) {
		Banda_id = banda_id;
	}

	public Integer getCd_id() {
		return Cd_id;
	}

	public void setCd_id(Integer cd_id) {
		Cd_id = cd_id;
	}
	

}
