package co.mycompany.restaurante.cliente.infra;

import co.mycompany.restaurante.cliente.domain.entity.Componente;
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
public class ClientServiceComponente {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = Utilities.loadProperty("base.uriComponente");

    public ClientServiceComponente() {
    }
    
    private void initComponente(){
        client = ClientBuilder.newClient();
        webTarget = webTarget = client.target(BASE_URI).path("componente");
    }
    
    public Componente getComponente(int idComponente){
        initComponente();
        String formato = java.text.MessageFormat.format("{0}", idComponente);
        webTarget = webTarget.path(formato);
        Class<Componente> responseType = Componente.class;
        Componente dato=null;
        try {
            dato = webTarget.request(MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
        }
        close();
        return dato;
    }
    
    public String deleteComponente(int idComponente){
        initComponente();
        String formato = java.text.MessageFormat.format("{0}", idComponente);
        webTarget = webTarget.path(formato);
        try {
            webTarget.request().delete(String.class);
            close();
            return "Componente eliminado correctamente";
        } catch (Exception e) {
            close();
            return "Error, el componente con ese id no existe";
        }
    }
    
    public String createComponente(Object requestEntity) throws ClientErrorException {
        initComponente();
        
        try {
            webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), String.class);          
            close();
            return "Componente añadido correctamente";
        } catch (Exception e) {
            close();
            return "Error, el componente con ese id y nombre ya existe";
        }      
    }
    
    public <T> List<Componente> findAllComponentes() throws javax.ws.rs.ClientErrorException {
        initComponente();
        GenericType<List<Componente>> listResponseType = new GenericType<List<Componente>>(){};
        List<Componente> componentes = webTarget.request(MediaType.APPLICATION_JSON).get(listResponseType);
        close();
        return componentes;
    } 
    
    
    
    private void close() {
        client.close();
    }
}
