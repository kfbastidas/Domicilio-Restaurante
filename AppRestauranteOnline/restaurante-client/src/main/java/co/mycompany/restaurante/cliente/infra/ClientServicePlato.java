package co.mycompany.restaurante.cliente.infra;

import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Plato;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Permite conectarse al servidor web y consultar la API Rest
 * @author Kevith Bastidas
 */
public class ClientServicePlato {
    
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = Utilities.loadProperty("base.uriPlato");

    public ClientServicePlato() {
    }
    
    private void initPlato(){
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("plato");
    }
    
    private void close() {
        client.close();
    }
    
     public String deletePlato(int idRestaurante, DiaSemana dia){
        initPlato();
        String formato = java.text.MessageFormat.format("{0}", idRestaurante);
        webTarget = webTarget.path(formato);
        formato = java.text.MessageFormat.format("{0}", dia);
        webTarget = webTarget.path(formato);
        try {
            webTarget.request().delete(String.class);
            close();
            return "Plato eliminado correctamente";
        } catch (Exception e) {
            close();
            return "Error, el plato con el idRestaurante y diaSemana no existe";
        }
    }
    
    public String updatePlato(int idRestaurante,DiaSemana dia, Plato plato){
        initPlato();
        String formato = java.text.MessageFormat.format("{0}", idRestaurante);
        webTarget = webTarget.path(formato);
        formato = java.text.MessageFormat.format("{0}", dia);
        webTarget = webTarget.path(formato);
        Object requestEntity = plato;
        String mensaje= webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class);
        close();
        return mensaje;
    }
    
    public Plato getPlato(int idRestaurante,DiaSemana dia){
        initPlato();
        String formato = java.text.MessageFormat.format("{0}", idRestaurante);
        webTarget = webTarget.path(formato);
        formato = java.text.MessageFormat.format("{0}", dia);
        webTarget = webTarget.path(formato);
        Class<Plato> responseType = Plato.class;
        Plato dato=null;
        try {
            dato = webTarget.request(MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
        }
        close();
        return dato;
    }
    
    public String addPlato(Object requestEntity) throws ClientErrorException {
        initPlato();
        try {
            webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class);
            close();
            return "Plato añadido correctamente";
        } catch (Exception e) {
            close();
            return "Error, el plato con ese idRest y dia ya existe";
        }
    }
    
    
}
