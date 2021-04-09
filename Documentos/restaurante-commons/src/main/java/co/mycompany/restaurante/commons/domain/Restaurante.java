package co.mycompany.restaurante.commons.domain;

import java.util.ArrayList;
/**
 * Restaurante de la tienda online
 *
 * @author kevith felipe bastidas
 */
public class Restaurante {
    private int id;
    private String nombre;
    private String direcccion;
    private String ciudad;
    private String telefono;
    private byte[] foto;
    private ArrayList<Componente> menuSemanal;
    private Plato plato;
  
    public Restaurante() {}
    
    public Restaurante(int id,String nombre,String direccion,String ciudad,String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direcccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.menuSemanal= new ArrayList<>();
        this.plato = new Plato();
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirecccion() {
        return direcccion;
    }

    public void setDirecccion(String direcccion) {
        this.direcccion = direcccion;
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

    public ArrayList<Componente> getMenuSemanal() {
        return menuSemanal;
    }

    public void setMenuSemanal(ArrayList<Componente> menuSemanal) {
        this.menuSemanal = menuSemanal;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "id=" + id + ", nombre=" + nombre + ", direcccion=" + direcccion + ", ciudad=" + ciudad + ", telefono=" + telefono + ", foto=" + foto + ", menuSemanal=" + menuSemanal + ", plato=" + plato + '}';
    }

    
    
    
}
