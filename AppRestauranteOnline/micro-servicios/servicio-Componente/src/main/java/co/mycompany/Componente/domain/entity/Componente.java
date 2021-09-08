package co.mycompany.Componente.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa a un componente, mapeado con la BD.
 * @author kevith Felipe Bastidas
 */
@Entity
@Table(name = "componente")
public class Componente implements Serializable{

	public static final int MAX = 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id",unique = true)
	private Long id;
	@Column(name = "nombre",nullable = false,length = MAX, unique = true, updatable = true)
	private String nombre;
	@Column(name = "tipo",nullable = false,length = MAX, updatable = true)
	private String tipo;
	
	/**
	 * Constructor
	 */
	public Componente(Long id, String nombre, String tipo) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Componente() {
		
	}
	
	/**
	 * gets and sets
	 */
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Componente [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
	
}
