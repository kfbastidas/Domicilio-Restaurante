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
public class Administrador {
    String usuario;
    String clave;
    ArrayList<Restaurante> restaurantes;

    public Administrador() {}
    
    public Administrador(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        this.restaurantes = new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    @Override
    public String toString() {
        return "Administrador{" + "usuario=" + usuario + ", clave=" + clave + ", restaurantes=" + restaurantes + '}';
    }
    
}
