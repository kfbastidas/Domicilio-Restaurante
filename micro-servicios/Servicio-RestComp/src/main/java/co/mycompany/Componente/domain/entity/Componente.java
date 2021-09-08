package co.mycompany.Componente.domain.entity;

/**
 * Representa a un componente.
 * @author kevith Felipe Bastidas
 */
public class Componente{

	private Long id;
	private String nombre;
	private String tipo;
	private String dia;
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
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	@Override
	public String toString() {
		return "Componente [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
	
}
