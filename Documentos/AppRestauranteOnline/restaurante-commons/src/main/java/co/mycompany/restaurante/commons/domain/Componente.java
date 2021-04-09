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
public class Componente {
    int id;
    String nombre;
    TipoComponente tipo;
    ArrayList<DiaSemana> dias;
    public Componente() {}

    public Componente(int id, String nombre, TipoComponente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.dias = new ArrayList<>();
    }

    public ArrayList<DiaSemana> getDias() {
        return dias;
    }

    public void setDias(ArrayList<DiaSemana> dias) {
        this.dias = dias;
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

    public TipoComponente getTipo() {
        return tipo;
    }

    public void setTipo(TipoComponente tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Componente{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", dias=" + dias + '}';
    }
    
}
