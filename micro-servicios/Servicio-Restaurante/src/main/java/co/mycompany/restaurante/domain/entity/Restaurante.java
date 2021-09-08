package co.mycompany.restaurante.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa a un restaurante, mapeado con la BD.
 * @author Kevith Felipe Bastidas
 */
@Entity
@Table(name ="restaurante")
public class Restaurante implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	* Atributos
	*/
	@Id
	@Column(name = "rest_id")
	private Long id;
	
	@Column(name = "rest_nombre")
	private String nombre;
	
	@Column(name = "rest_direccion")
	private String direccion;
	
	@Column(name = "rest_ciudad")
	private String ciudad;
	
	@Column(name = "rest_telefono")
	private String telefono;

	/**
	 * 
	 * Getters and Setters
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}