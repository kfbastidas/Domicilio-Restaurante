package co.mycompany.Componente.domain.entity;

import java.io.Serializable;

/**
 * Representa a un restaurante, mapeado con la BD.
 * @author Kevith Felipe Bastidas
 */
public class Restaurante implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	* Atributos
	*/
	private Long id;
	
	private String nombre;
	
	private String direccion;
	
	private String ciudad;
	
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