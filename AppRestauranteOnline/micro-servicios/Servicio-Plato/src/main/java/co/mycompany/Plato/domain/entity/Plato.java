package co.mycompany.Plato.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa un plato de un respectivo restaurante. Mapeado con la bd.
 * 
 * @author Kevith Felipe Bastidas
 */
@Entity
@Table(name = "plato")
public class Plato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlato;

	@Column(name = "precio")
	private Long precio;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "cantidad")
	private Long cantidad;

	@Column(name = "url_imagen")
	private String url_imagen;
	
	@Column(name = "rest_id")
	private Long rest_id;
	
	@Column(name = "dia")
	private String dia;
	
	public Plato() {
		
	}

	public Plato(Long idPlato, Long precio, String descripcion,String url_imagen,Long cantidad, Long rest_id, String dia) {
		super();
		this.idPlato = idPlato;
		this.precio = precio;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.url_imagen = url_imagen;
		this.rest_id = rest_id;
		this.dia = dia;
	}

	public Long getIdPlato() {
		return idPlato;
	}

	public void setIdPlato(Long idPlato) {
		this.idPlato = idPlato;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getRest_id() {
		return rest_id;
	}

	public void setRest_id(Long rest_id) {
		this.rest_id = rest_id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getUrl_imagen() {
		return url_imagen;
	}

	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}

	@Override
	public String toString() {
		return "Plato [id=" + idPlato + ", precio=" + precio + ", descripcion=" + descripcion + ", cantidad=" + cantidad
				+ ", idRest=" + rest_id + ", dia=" + dia + "]";
	}
	
	
	
}