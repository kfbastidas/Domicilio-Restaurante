package co.mycompany.restaurante.cliente.access;
import co.mycompany.restaurante.commons.infra.Protocol;
import co.mycompany.restaurante.commons.infra.JsonError;
import co.mycompany.restaurante.cliente.infra.SocketRestaurante;
import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Plato;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.mycompany.restaurante.commons.domain.Restaurante;
import java.util.Arrays;
import java.util.List;
/**
 * Servicio de Cliente. Permite hacer el CRUD de clientes solicitando los
 * servicios con la aplicación server. Maneja los errores devueltos
 *
 * @author Kevith Felipe Bastidas
 */
public class RestauranteAccessImplSockets implements IRestauranteAccess {
    /**
     * El servicio utiliza un socket para comunicarse con la aplicación server
     */
    private SocketRestaurante mySocket;
    public RestauranteAccessImplSockets() {
        mySocket = new SocketRestaurante();
    }
    /**
     * Actualiza el plato en un restaurante determinado
     * @param idRestaurante
     * @param plato
     * @return 
     */
    @Override
    public String updatePlato(int idRestaurante,Plato plato){
        String jsonResponse = null;
        String requestJson = addUpdatePlatoRequestJson(idRestaurante,plato);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return "No se pudo conectar con el servidor";
        }
        if (jsonResponse.contains("error")) {
            //Devolvió algún error                
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
            return extractMessages(jsonResponse);
        } 
        //Agregó correctamente el menu, devuelve los nombres de los platos
        return jsonResponse;
    }
    /**
     * Crea una solicitud json para ser enviada por el socket
     * @param componente componente del plato
     * @return 
     */
    private String addUpdatePlatoRequestJson(int idRestaurante,Plato plato) {

        Protocol protocol = new Protocol();
        protocol.setResource("plato");
        protocol.setAction("update");
        protocol.addParameter("rest_Id", String.valueOf(idRestaurante));
        protocol.addParameter("pla_id",Integer.toString(plato.getId()));
        protocol.addParameter("pla_descripcion", plato.getDescripcion());
        protocol.addParameter("pla_precio", Integer.toString(plato.getPrecio()));
        protocol.addParameter("pla_cantidad", Integer.toString(plato.getCantidad()));

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    } 
    @Override
    public String deleteComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        String jsonResponse = null;
        String requestJson = deleteComponenteSemanalRequestJson(idRestaurante,componente,dia);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return "No se pudo conectar con el servidor";
        }
        if (jsonResponse.contains("error")) {
            //Devolvió algún error                
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
            return extractMessages(jsonResponse);
        } 
        //Agregó correctamente el menu, devuelve los nombres de los platos
        return jsonResponse;
    }
     /**
     * Crea una solicitud json para ser enviada por el socket
     * @param componente componente del plato
     * @return 
     */
    private String deleteComponenteSemanalRequestJson(int idRestaurante,Componente componente,DiaSemana dia) {

        Protocol protocol = new Protocol();
        protocol.setResource("componenteSemanal");
        protocol.setAction("delete");
        protocol.addParameter("rest_Id", String.valueOf(idRestaurante));
        protocol.addParameter("dia", dia.name());
        protocol.addParameter("Id", Integer.toString(componente.getId()));
        protocol.addParameter("Nombre", componente.getNombre());
        protocol.addParameter("Tipo", componente.getTipo().name());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    } 
    @Override
    public String addComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        String jsonResponse = null;
        String requestJson = addComponenteSemanalRequestJson(idRestaurante,componente,dia);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return "No se pudo conectar con el servidor";
        }
        if (jsonResponse.contains("error")) {
            //Devolvió algún error                
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
            return extractMessages(jsonResponse);
        } 
        //Agregó correctamente el menu, devuelve los nombres de los platos
        return jsonResponse;
    }
     /**
     * Crea una solicitud json para ser enviada por el socket
     * @param componente componente del plato
     * @return 
     */
    private String addComponenteSemanalRequestJson(int idRestaurante,Componente componente,DiaSemana dia) {

        Protocol protocol = new Protocol();
        protocol.setResource("componenteSemanal");
        protocol.setAction("set");
        protocol.addParameter("rest_Id", String.valueOf(idRestaurante));
        protocol.addParameter("dia", dia.name());
        protocol.addParameter("Id", Integer.toString(componente.getId()));
        protocol.addParameter("Nombre", componente.getNombre());
        protocol.addParameter("Tipo", componente.getTipo().name());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    } 
    @Override
    public String addComponente(Componente componente) {
        String jsonResponse = null;
        String requestJson = addComponenteRequestJson(componente);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return "No se pudo conectar con el servidor";
        }
        if (jsonResponse.contains("error")) {
            //Devolvió algún error                
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
            return extractMessages(jsonResponse);
        } 
        //Agregó correctamente el menu, devuelve los nombres de los platos
        return jsonResponse;
    }   
    /**
     * Crea una solicitud json para ser enviada por el socket
     * @param componente componente del plato
     * @return 
     */
    private String addComponenteRequestJson(Componente componente) {

        Protocol protocol = new Protocol();
        protocol.setResource("componente");
        protocol.setAction("set");
        protocol.addParameter("Id", Integer.toString(componente.getId()));
        protocol.addParameter("Nombre", componente.getNombre());
        protocol.addParameter("Tipo", componente.getTipo().name());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    } 
    @Override
    public ArrayList<Restaurante> getRestaurantes(){
        String jsonResponse = null;
        String requestJson = getRestauranteRequestJson();
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return new ArrayList<>();
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                return new ArrayList<>();
            } else {
                if (jsonResponse.contains("vacio")){
                    return null;
                }
                //Extrajo correctamente los platos para devolver un arrayList de estos
                return jsonResToArray(jsonResponse);
            }
        }
    }
    private String getRestauranteRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("restaurantes");
        protocol.setAction("get");
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    } 
    private ArrayList<Restaurante> jsonResToArray(String jsonRestaurante) {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        String jsonAux = jsonRestaurante.replace("[", "");
        jsonAux = jsonAux.replace("]", "");
        List<String> jsonRestaurantes = Arrays.asList(jsonAux.split("},"));
        for (String jsonRes : jsonRestaurantes) {
            if (jsonRes.contains("}") == false) {
                jsonRes += "}";
            }
            restaurantes.add(jsonToRestaurante(jsonRes));
        }
        return restaurantes;
    }
    /**
     * Convierte jsonRestaurante, proveniente del server socket, de json a un
     * objeto Restaurante
     *
     * @param jsonRestaurante objeto cliente en formato json
     */
    private Restaurante jsonToRestaurante(String jsonRestaurante) {

        Gson gson = new Gson();
        //{"id":1,"nombre":"LA COSECHA","direcccion":"Cra 11 # 3-45","ciudad":"Popayan","telefono":"800001","menuSemanal":,"plato":{"id":0,"precio":0,"cantidad":0}
        //{"id":1,"nombre":"LA COSECHA","direcccion":"Cra 11 # 3-45","ciudad":"Popayan","telefono":"800001"}
        Restaurante restaurante = gson.fromJson(jsonRestaurante, Restaurante.class);
        return restaurante;

    } 
    @Override
    public ArrayList<Componente> getComponentes(){
        String jsonResponse = null;
        //{"resource":"componentes","action":"get","parameters":[{"name":"rest_id","value":"1"},{"name":"dia","value":"LUNES"}]}
        String requestJson = getComponentesRequestJson();
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return new ArrayList<>();
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                return new ArrayList<>();
            } else {
                if (jsonResponse.contains("vacio")){
                    return null;
                }
                //Extrajo correctamente los platos para devolver un arrayList de estos
                return jsonCompToArray(jsonResponse);
            }
        }
    }
    private String getComponentesRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("componentes");
        protocol.setAction("get");
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    } 
    @Override
    public ArrayList<Componente> getMenuComponentes(int idRestaurante,DiaSemana dia) {
        String jsonResponse = null;
        //{"resource":"componentes","action":"get","parameters":[{"name":"rest_id","value":"1"},{"name":"dia","value":"LUNES"}]}
        String requestJson = getComponentesRequestJson(idRestaurante,dia);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null || jsonResponse.equals("[]")) {
            return new ArrayList<>();
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                return new ArrayList<>();
            } else {
                if (jsonResponse.contains("vacio")){
                    return null;
                }
                //Extrajo correctamente los platos para devolver un arrayList de estos
                return jsonCompToArray(jsonResponse);
            }
        }
    }
    private String getComponentesRequestJson(int idRestaurante,DiaSemana dia) {
        Protocol protocol = new Protocol();
        protocol.setResource("componentes");
        protocol.setAction("getMenu");
        protocol.addParameter("rest_id", String.valueOf(idRestaurante));
        protocol.addParameter("dia", dia.name());
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    } 
    private ArrayList<Componente> jsonCompToArray(String jsonRestaurante) {
        ArrayList<Componente> componentes = new ArrayList<>();
        String jsonAux = jsonRestaurante.replace("[", "");
        jsonAux = jsonAux.replace("]", "");
        List<String> jsonComponentes = Arrays.asList(jsonAux.split("},"));
        for (String jsonRes : jsonComponentes) {
            if (jsonRes.contains("}") == false) {
                jsonRes += "}";
            }
            componentes.add(jsonToComponente(jsonRes));
        }
        return componentes;
    }
    /**
     * Convierte jsonRestaurante, proveniente del server socket, de json a un
     * objeto Restaurante
     *
     * @param jsonRestaurante objeto cliente en formato json
     */
    private Componente jsonToComponente(String jsonRestaurante) {
        Gson gson = new Gson();
        Componente componente = gson.fromJson(jsonRestaurante, Componente.class);
        return componente;
    } 
    @Override
    public String getAdministrador(String usuario){
        String jsonResponse = null;
        //{"resource":"administrador","action":"get","parameters":[{"name":"rest_id","value":"1"},{"name":"dia","value":"LUNES"}]}
        String requestJson = getAdministradorRequestJson(usuario);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return "No se pudo conectar con el servidor";
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                return extractMessages(jsonResponse);
            } else {
                if (jsonResponse.contains("vacio")){
                    return null;
                }
                //Extrajo correctamente los platos para devolver un arrayList de estos
                return jsonResponse;
            }
        }
    }
    private String getAdministradorRequestJson(String usuario) {
        Protocol protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("get");
        protocol.addParameter("adm_usuario", usuario);
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    } 
    @Override
    public Plato getPlato(int idRestaurantes){
        String jsonResponse = null;
        String requestJson = getPlatoRequestJson(idRestaurantes);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return new Plato();
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(RestauranteAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                return new Plato();
            } else {
                if (jsonResponse.contains("vacio")){
                    return null;
                }
                //Extrajo correctamente los platos para devolver un arrayList de estos
                return jsonToPlato(jsonResponse);
            }
        }
    }
    private String getPlatoRequestJson(int idRestaurante) {
        
        Protocol protocol = new Protocol();
        protocol.setResource("plato");
        protocol.setAction("get");
        protocol.addParameter("rest_id", Integer.toString(idRestaurante));

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    private Plato jsonToPlato(String jsonPlato){
        
        Gson gson = new Gson();
        Plato plato = gson.fromJson(jsonPlato, Plato.class);
        return plato;
    }   
    
    //------------------------------------------------------------------------------------------------------
 
    /**
     * Extra los mensajes de la lista de errores
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }  
}
