package co.mycompany.Componente.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Kevith Felipe Bastidas
 *
 */
@Entity
@Table(name = "restcomponente")
public class RestComponente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "idrest",nullable = false)
	private Long idRest;
	@Column(name = "idcomp",nullable = false)
	private Long idComp;
	@Column(name = "dia",nullable = false)
	private String dia;
	
	/**
	 * Constructor
	 */
	public RestComponente(Long id, Long idRest, Long idComp, String dia) {
		this.id = id;
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "RestComponente [id=" + id + ", idRest=" + idRest + ", idComp=" + idComp + ", dia=" + dia + "]";
	}

	
}
