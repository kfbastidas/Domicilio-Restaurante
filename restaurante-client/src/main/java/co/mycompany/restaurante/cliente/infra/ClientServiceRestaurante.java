package co.mycompany.restaurante.cliente.infra;

import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
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
public class ClientServiceRestaurante {
    
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = Utilities.loadProperty("base.uriRestaurante");

    public ClientServiceRestaurante() {
    }
    
    
    private void initRestaurante(){
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("restaurante");
    }
    
    private void close() {
        client.close();
    }
    
    public String updateRestaurante(Object requestEntity,String id) throws ClientErrorException {
        initRestaurante();
        webTarget = webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        String mensaje = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class);
        close();
        return mensaje;
    }
    
    public Restaurante getRestaurante(int idRestaurante){
        initRestaurante();
        String formato = java.text.MessageFormat.format("{0}", idRestaurante);
        webTarget = webTarget.path(formato);
        Class<Restaurante> responseType = Restaurante.class;
        Restaurante dato=null;
        try {
            dato = webTarget.request(MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
        }
        close();
        return dato;
    }
    
    public String addRestaurante(Object requestEntity) throws ClientErrorException {
        initRestaurante();
        try {
            webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class);
            close();
            return "Restaurante añadido correctamente";
        } catch (Exception e) {
            close();
            return "Error, el restaurante con ese id ya existe";
        }
    }
    
    public <T> List<Restaurante> findAllRestaurantes() throws javax.ws.rs.ClientErrorException {
        initRestaurante();
        GenericType<List<Restaurante>> listResponseType = new GenericType<List<Restaurante>>(){};
        List<Restaurante> restaurantes = webTarget.request(MediaType.APPLICATION_JSON).get(listResponseType);
        close();
        return restaurantes;
    } 
    
    public String deleteRestaurante(int idRestaurante){
        initRestaurante();
        String formato = java.text.MessageFormat.format("{0}", idRestaurante);
        webTarget = webTarget.path(formato);
        try {
            webTarget.request().delete(String.class);
            close();
            return "Restaurante eliminado correctamente";
        } catch (Exception e) {
            close();
            return "Error, el restaurante con ese id no existe";
        }
    }

    
}
