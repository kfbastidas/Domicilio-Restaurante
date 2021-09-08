/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.cliente.infra;

import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Permite conectarse al servidor web y consultar la API Rest
 * @author Kevith Bastidas
 */
public class ClientServiceRestComp {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = Utilities.loadProperty("base.uriRestComp");

    public ClientServiceRestComp() {
    }
    
    
    private void initRestComponente(){
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("restcomponente");
    }
    
    public String addComponenteSemanal(int idRestaurante, Componente componente, DiaSemana dia){
        initRestComponente();
        RestComponente restComp = new RestComponente(idRestaurante, componente.getId(), dia.name());
        Object requestEntity = restComp;
        try {
            webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class);
            close();
            return "ComponenteSemanal añadido correctamente";
        } catch (Exception e) {
            close();
            return "Error, el componenteSemanal con ese id del restaurante y diaSemana ya existe";
        }
    }
    
    public String deleteComponenteSemanal(int idRestaurante, int idComponente, DiaSemana dia){
        initRestComponente();
        String formato = java.text.MessageFormat.format("{0}", idRestaurante);
        webTarget = webTarget.path(formato);
        formato = java.text.MessageFormat.format("{0}", idComponente);
        webTarget = webTarget.path(formato);
        formato = java.text.MessageFormat.format("{0}", dia);
        webTarget = webTarget.path(formato);
        try {
            webTarget.request().delete(String.class);
            close();
            return "ComponenteSemanal eliminado correctamente";
        } catch (Exception e) {
            close();
            return "Error, el componenteSemanal con el id del restuarante y dia de la semana no existe";
        }
        
    }
    
    public List<Componente> getMenuComponentes(int idRestaurante,DiaSemana dia)throws ClientErrorException{
        initRestComponente();
        GenericType<List<Componente>> listResponseType = new GenericType<List<Componente>>(){};
        String formato = java.text.MessageFormat.format("{0}", idRestaurante);
        webTarget = webTarget.path(formato);
        formato = java.text.MessageFormat.format("{0}", dia);
        webTarget = webTarget.path(formato);
        List<Componente> componentes = new ArrayList<>();
        try {
            componentes = webTarget.request(MediaType.APPLICATION_JSON).get(listResponseType);
        } catch (Exception e) {
        }
        close();
        return componentes;
    }
    
    
    
    private void close() {
        client.close();
    }
}
