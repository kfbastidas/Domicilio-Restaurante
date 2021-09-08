package co.mycompany.restaurante.cliente.infra;

import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Plato;
import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
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
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class NewJerseyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = Utilities.loadProperty("base.uri");

    public NewJerseyClient() {
    }
    
    private void close() {
        client.close();
    }
    
    /* MANEJO DE PETICIONES DEL PLATO */
    
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
    
    private void initPlato(){
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:8004/plato");
    }
    
    /* MANEJO DE PETICIONES DEL RESTUARANTE Y COMPONENTE */
    
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
    
    private void initRestComponente(){
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:8006/restcomponente");
    }
    
    /* MANEJO DE PETICIONES DEL COMPONENTE */
    
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
    
    private void initComponente(){
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:8001/componente");
    }
    
    /* MANEJO DE PETICIONES DEL RESTUARANTE */
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

    private void initRestaurante(){
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:8002/restaurante");
    }
    
}
