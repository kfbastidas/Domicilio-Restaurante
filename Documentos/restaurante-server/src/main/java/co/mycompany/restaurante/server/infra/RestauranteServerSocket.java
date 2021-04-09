package co.mycompany.restaurante.server.infra;

import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.commons.infra.JsonError;
import co.mycompany.restaurante.commons.infra.Protocol;
import co.mycompany.restaurante.commons.infra.Utilities;
import co.mycompany.restaurante.server.access.Factory;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.mycompany.restaurante.server.domain.services.RestauranteService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import co.mycompany.restaurante.commons.domain.Plato;
import co.mycompany.restaurante.commons.domain.TipoComponente;
import co.mycompany.restaurante.server.access.IRestauranteRepository;

/**
 * Servidor Socket que está escuchando permanentemente solicitudes de los
 * clientes. Cada solicitud la atiende en un hilo de ejecución
 *
 * @author Kevith Felipe Bastidas
 */
public class RestauranteServerSocket implements Runnable {

    /**
     * Servicio de clientes
     */
    private final RestauranteService service;
    /**
     * Server Socket, la orejita
     */
    private static ServerSocket ssock;
    /**
     * Socket por donde se hace la petición/respuesta
     */
    private static Socket socket;
    /**
     * Permite leer un flujo de datos del socket
     */
    private Scanner input;
    /**
     * Permite escribir un flujo de datos del scoket
     */
    private PrintStream output;
    /**
     * Puerto por donde escucha el server socket
     */
    private static final int PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
    /**
     * Constructor
     */
    public RestauranteServerSocket() {
        // Se hace la inyección de dependencia
        IRestauranteRepository repository = Factory.getInstance().getRepository();
        service = new RestauranteService(repository);
    }
    /**
     * Arranca el servidor y hace la estructura completa
     */
    public void start() {
        openPort();

        while (true) {
            waitToClient();
            throwThread();
        }
    }
    /**
     * Lanza el hilo
     */
    private static void throwThread() {
        new Thread(new RestauranteServerSocket()).start();
    }
    /**
     * Instancia el server socket y abre el puerto respectivo
     */
    private static void openPort() {
        try {
            ssock = new ServerSocket(PORT);
            Logger.getLogger("Server").log(Level.INFO, "Servidor iniciado, escuchando por el puerto {0}", PORT);
        } catch (IOException ex) {
            Logger.getLogger(RestauranteServerSocket.class.getName()).log(Level.SEVERE, "Error del server socket al abrir el puerto", ex);
        }
    }
    /**
     * Espera que el cliente se conecta y le devuelve un socket
     */
    private static void waitToClient() {
        try {
            socket = ssock.accept();
            Logger.getLogger("Socket").log(Level.INFO, "Socket conectado");
        } catch (IOException ex) {
            Logger.getLogger(RestauranteServerSocket.class.getName()).log(Level.SEVERE, "Eror al abrir un socket", ex);
        }
    }
    /**
     * Cuerpo del hilo
     */
    @Override
    public void run() {
        try {
            createStreams();
            readStream();
            closeStream();

        } catch (IOException ex) {
            Logger.getLogger(RestauranteServerSocket.class.getName()).log(Level.SEVERE, "Eror al leer el flujo", ex);
        }
    }
    /**
     * Crea los flujos con el socket
     *
     * @throws IOException
     */
    private void createStreams() throws IOException {
        output = new PrintStream(socket.getOutputStream());
        input = new Scanner(socket.getInputStream());
    }
    /**
     * Lee el flujo del socket
     */
    private void readStream() {
        if (input.hasNextLine()) {
            // Extrae el flujo que envió la aplicación cliente
            String request = input.nextLine();
            processRequest(request);

        } else {
            output.flush();
            String errorJson = generateErrorJson();
            output.println(errorJson);
        }
    }
    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"restaurante","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
    private void processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);

        switch (protocolRequest.getResource()) {
            case "componente":
                processSetComponente(protocolRequest);
                break;
            case "restaurantes":
                if (protocolRequest.getAction().equals("get")) {
                    // Obtener datos de restaurante
                    processGetRestaurantes();
                }
                break;
            case "componentes":
                if (protocolRequest.getAction().equals("getMenu")) {
                    //{"resource":"componentes","action":"get","parameters":[{"name":"rest_id","value":"1"},{"name":"dia","value":"LUNES"}]}
                    processGetMenuComponentes(protocolRequest);
                }else if(protocolRequest.getAction().equals("get")){
                    processGetComponentes();
                }
                
                break;
            case "administrador":
                processGetAdministrador(protocolRequest);
                break;
            case "plato":
                if(protocolRequest.getAction().equals("get")){
                    processGetPlato(protocolRequest);
                }else if(protocolRequest.getAction().equals("update")){
                    processUpdatePlato(protocolRequest);
                }
                
                break;
            case "componenteSemanal":
                if(protocolRequest.getAction().equals("delete")){
                    processDeleteComponenteSemanal(protocolRequest);
                }else if(protocolRequest.getAction().equals("set")){
                    processSetComponenteSemanal(protocolRequest);
                }
        }

    }
    /**
     * Procesa la solicitud de agregar un Componente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processUpdatePlato(Protocol protocolRequest) {
        //Protocol{resource=componente, action=set, 
        //parameters=[Parameter{name=Id, value=12}, Parameter{name=Nombre, value=jugo de lulo}, Parameter{name=Tipo, value=BEBIDA}]}
        Plato plato = new Plato();
        int cont = 0;
        int idRestaurante = Integer.parseInt(protocolRequest.getParameters().get(cont++).getValue());
        plato.setId(Integer.parseInt(protocolRequest.getParameters().get(cont++).getValue()));       
        plato.setDescripcion(protocolRequest.getParameters().get(cont++).getValue());
        plato.setPrecio(Integer.parseInt(protocolRequest.getParameters().get(cont++).getValue())); 
        plato.setCantidad(Integer.parseInt(protocolRequest.getParameters().get(cont).getValue())); 
        String response = service.updatePlato(idRestaurante, plato);
        output.println(response);
    }  
    /**
     * Procesa la solicitud de agregar un Componente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processSetComponenteSemanal(Protocol protocolRequest) {
        //Protocol{resource=componente, action=set, 
        //parameters=[Parameter{name=Id, value=12}, Parameter{name=Nombre, value=jugo de lulo}, Parameter{name=Tipo, value=BEBIDA}]}
        Componente componente = new Componente();
        int cont = 0;
        int idRestaurante = Integer.parseInt(protocolRequest.getParameters().get(cont++).getValue());
        DiaSemana dia = DiaSemana.valueOf(protocolRequest.getParameters().get(cont++).getValue());
        componente.setId(Integer.parseInt(protocolRequest.getParameters().get(cont++).getValue()));

        componente.setNombre(protocolRequest.getParameters().get(cont++).getValue());

        componente.setTipo(TipoComponente.valueOf(protocolRequest.getParameters().get(cont).getValue()));
        String response = service.addComponenteSemanal(idRestaurante, componente, dia);
        output.println(response);
    }   
    /**
     * Procesa la solicitud de agregar un Componente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processDeleteComponenteSemanal(Protocol protocolRequest) {
        //Protocol{resource=componenteSemanal, action=delete, 
        //parameters=[Parameter{name=Id, value=12}, Parameter{name=Nombre, value=jugo de lulo}, Parameter{name=Tipo, value=BEBIDA}]}
        Componente componente = new Componente();
        int cont = 0;
        int idRestaurante = Integer.parseInt(protocolRequest.getParameters().get(cont++).getValue());
        DiaSemana dia = DiaSemana.valueOf(protocolRequest.getParameters().get(cont++).getValue());
        componente.setId(Integer.parseInt(protocolRequest.getParameters().get(cont++).getValue()));

        componente.setNombre(protocolRequest.getParameters().get(cont++).getValue());

        componente.setTipo(TipoComponente.valueOf(protocolRequest.getParameters().get(cont).getValue()));
        String response = service.deleteComponenteSemanal(idRestaurante, componente,dia);
        output.println(response);
    } 
    /**
     * Procesa la solicitud de agregar un Componente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processGetComponentes() {
        ArrayList<Componente> componentes = service.getComponentes();
        String response = objectCompToJSON(componentes);     
        output.println(response);
    }  
    /**
     * Procesa la solicitud de agregar un Componente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processGetMenuComponentes(Protocol protocolRequest) {
        ArrayList<Componente> componentes = service.getMenuComponentes(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()), DiaSemana.valueOf(protocolRequest.getParameters().get(1).getValue()));       
        String response = objectCompToJSON(componentes);     
        output.println(response);
    }   
    private String objectCompToJSON(ArrayList<Componente> componentes) {
        Gson gson = new Gson();
        String strObject = gson.toJson(componentes);
        return strObject;
    }  
    private String objectPlatoToJSON(Plato plato) {
        Gson gson = new Gson();
        String strObject = gson.toJson(plato);
        return strObject;
    }  
    private void processGetPlato(Protocol protocolRequest) {
        Plato plato = service.getPlato(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        String response = objectPlatoToJSON(plato);     
        output.println(response);
    }   
    private void processGetAdministrador(Protocol protocolRequest) {
        String clave = service.getAdministrador(protocolRequest.getParameters().get(0).getValue());          
        output.println(clave);
    } 
     /**
     * Procesa la solicitud de agregar un Componente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processSetComponente(Protocol protocolRequest) {
        //Protocol{resource=componente, action=set, 
        //parameters=[Parameter{name=Id, value=12}, Parameter{name=Nombre, value=jugo de lulo}, Parameter{name=Tipo, value=BEBIDA}]}
        Componente componente = new Componente();
        int cont = 0;
        componente.setId(Integer.parseInt(protocolRequest.getParameters().get(cont).getValue()));
        cont++;
        componente.setNombre(protocolRequest.getParameters().get(cont).getValue());
        cont++;
        componente.setTipo(TipoComponente.valueOf(protocolRequest.getParameters().get(cont).getValue()));
        String response = service.addComponente(componente);        
        output.println(response);
    }     
    private void processGetRestaurantes() {  
        ArrayList<Restaurante> Restaurantes = service.getRestaurantes();
        if (Restaurantes.isEmpty()) {
            output.println("Restaurantes vacio");
        } else {
            output.println(objectRestToJSON(Restaurantes));
        }
    }   
    /**
     * Genera un ErrorJson genérico
     *
     * @return error en formato json
     */
    private String generateErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("400");
        error.setError("BAD_REQUEST");
        error.setMessage("Error en la solicitud");
        errors.add(error);

        Gson gson = new Gson();
        String errorJson = gson.toJson(errors);

        return errorJson;
    }
    /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void closeStream() throws IOException {
        output.close();
        input.close();
        socket.close();
    }
    private String objectRestToJSON(ArrayList<Restaurante> restaurantes) {
        Gson gson = new Gson();
        String strObject = gson.toJson(restaurantes);
        return strObject;
    }
}
