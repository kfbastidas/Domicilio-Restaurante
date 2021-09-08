/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.cliente.domain.entity;

import java.util.ArrayList;

/**
 *
 * @author kevith felipe bastidas
 */
public class Plato {
    private int idPlato;
    private String descripcion;
    private int precio;
    private int cantidad;
    private DiaSemana dia;
    private int rest_id;
    private byte[] foto;
    private String url_imagen;
    private ArrayList<Componente> componentes;
    public Plato(){}

    public Plato(int idPlato, String descripcion, int precio, int cantidad,String url_imagen, DiaSemana dia, int rest_id) {
        this.idPlato = idPlato;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.url_imagen = url_imagen;
        this.dia = dia;
        this.rest_id = rest_id;
    }

    public Plato(String descripcion, int precio, int cantidad,String urlImagen, DiaSemana dia, int rest_id) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.dia = dia;
        this.rest_id = rest_id;
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

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    @Override
    public String toString() {
        return "Plato{" + "idPlato=" + idPlato + ", descripcion=" + descripcion + ", precio=" + precio + ", cantidad=" + cantidad + ", dia=" + dia + ", rest_id=" + rest_id + '}';
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }
}
