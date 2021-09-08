package co.mycompany.Componente.domain.entity;

import java.io.Serializable;


/**
 * 
 * @author Kevith Felipe Bastidas
 *
 */
//@Entity
//@Table(name = "restcomponente")
public class RestComponente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Column(name = "idRest",nullable = false,unique = true)
	private Long idRest;
	//@Column(name = "idComp",nullable = false,unique = true)
	private Long idComp;
	//@Column(name = "dia",nullable = false)
	private String dia;
	
	/**
	 * Constructor
	 */
	public RestComponente(Long idRest, Long idComp, String dia) {
		this.idRest = idRest;
		this.idComp = idComp;
		this.dia = dia;
	}
	
	public RestComponente() {
		
	}

	/**
	 * Gets and Sets
	 */
	
	public Long getIdRest() {
		return idRest;
	}

	public void setIdRest(Long idRest) {
		this.idRest = idRest;
	}

	public Long getIdComp() {
		return idComp;
	}

	public void setIdComp(Long idComp) {
		this.idComp = idComp;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "RestComponente [idRest=" + idRest + ", idComp=" + idComp + ", dia=" + dia + "]";
	}
	
}
