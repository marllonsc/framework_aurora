package framework.aurora.db.examples;

import java.util.Date;

public class Defeitos {
	
	String id;
	String equipe;
	String userName;
	String priorizacao;
	String codigo;
	String SLA;
	String observacao;
	Date dataDaData;
	Date dataEntrega;
	String statusDesc;
	String sumario;
	
	public Defeitos(String id, String equipe, String userName, String priorizacao, String codigo, String sLA,
			String observacao, Date dataDaData, Date dataEntrega, String statusDesc, String sumario) {
		super();
		this.id = id;
		this.equipe = equipe;
		this.userName = userName;
		this.priorizacao = priorizacao;
		this.codigo = codigo;
		SLA = sLA;
		this.observacao = observacao;
		this.dataDaData = dataDaData;
		this.dataEntrega = dataEntrega;
		this.statusDesc = statusDesc;
		this.sumario = sumario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEquipe() {
		return equipe;
	}
	
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPriorizacao() {
		return priorizacao;
	}
	
	public void setPriorizacao(String priorizacao) {
		this.priorizacao = priorizacao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getSLA() {
		return SLA;
	}
	
	public void setSLA(String sLA) {
		SLA = sLA;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Date getDataDaData() {
		return dataDaData;
	}
	
	public void setDataDaData(Date dataDaData) {
		this.dataDaData = dataDaData;
	}
	
	public Date getDataEntrega() {
		return dataEntrega;
	}
	
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}
	
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public String getSumario() {
		return sumario;
	}
	
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	
}
