/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.cliente.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kevith Bastidas
 */
public class Restaurante implements Serializable{
    
    private int id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private byte[] foto;
    private ArrayList<Componente> menuSemanal;
    private Plato plato;
 
    public Restaurante(int id, String nombre, String direccion, String ciudad, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.menuSemanal= new ArrayList<>();
        this.plato = new Plato();
    }

    public Restaurante() {
        this.menuSemanal= new ArrayList<>();
        this.plato = new Plato();
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public ArrayList<Componente> getMenuSemanal() {
        return menuSemanal;
    }

    public void setMenuSemanal(ArrayList<Componente> menuSemanal) {
        this.menuSemanal = menuSemanal;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
    
    @Override
    public String toString() {
        return "Restaurante{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + ", telefono=" + telefono + '}';
    }
    
}
