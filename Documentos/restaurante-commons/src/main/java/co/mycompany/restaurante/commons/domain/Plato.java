/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.commons.domain;

import java.util.ArrayList;

/**
 *
 * @author kevith felipe bastidas
 */
public class Plato {
    private int id;
    private String descripcion;
    private int precio;
    private int cantidad;
    private byte[] foto;
    private ArrayList<Componente> componentes;
    public Plato(){}

    public Plato(int id, String descripcion, int precio, int cantidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.componentes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(ArrayList<Componente> componentes) {
        this.componentes = componentes;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Plato{" + "id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + ", cantidad=" + cantidad + ", componentes=" + componentes + '}';
    }
    
}
