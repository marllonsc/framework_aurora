package temp.test;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "defeito") 
public class Defeito implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
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
	
	public Defeito() {}
	
	public Defeito(String id, String equipe, String userName, String priorizacao, String codigo, String sLA,
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

	@XmlElement 
	public void setId(String id) {
		this.id = id;
	}

	public String getEquipe() {
		return equipe;
	}
	
	@XmlElement 
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	
	public String getUserName() {
		return userName;
	}
	
	@XmlElement 
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPriorizacao() {
		return priorizacao;
	}
	
	@XmlElement 
	public void setPriorizacao(String priorizacao) {
		this.priorizacao = priorizacao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	@XmlElement 
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getSLA() {
		return SLA;
	}
	
	@XmlElement 
	public void setSLA(String sLA) {
		SLA = sLA;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	@XmlElement 
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Date getDataDaData() {
		return dataDaData;
	}
	
	@XmlElement 
	public void setDataDaData(Date dataDaData) {
		this.dataDaData = dataDaData;
	}
	
	public Date getDataEntrega() {
		return dataEntrega;
	}
	
	@XmlElement 
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}
	
	@XmlElement 
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public String getSumario() {
		return sumario;
	}
	
	@XmlElement 
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

}
