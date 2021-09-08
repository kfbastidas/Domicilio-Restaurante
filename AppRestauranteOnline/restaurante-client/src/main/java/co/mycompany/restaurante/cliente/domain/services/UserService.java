package co.mycompany.restaurante.cliente.domain.services;
import co.mycompany.restaurante.cliente.domain.TipoUser;
import co.mycompany.restaurante.cliente.domain.User;
import static co.mycompany.restaurante.cliente.infra.Messages.successMessage;
import co.mycompany.restaurante.cliente.infra.Security;
import java.util.ArrayList;
/**
 * Servicio de usuarios del sistema
 * @author Kevith Felipe Bastidas
 */
public class UserService {
    /**
     * autentica el usuario si y solo si existe el username y la contraseña
     * es correcta.
     * @param username nombre de usuario
     * @param password constraseña
     * @return true si inicio seccion, false si no inicio seccion
     */
    public static boolean autenticacion(String username, String password) {
        
        if (!validarUser(username, password)) {
            return false;
        }
        
        ArrayList<User> usuarios = usersAdmistrador();
        for (User usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                Security.usuario = usuario;
                return true;
            }
        }
        
        usuarios = usersClientes();
        for (User usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                Security.usuario = usuario;
                return true;
            }
        }
        successMessage("Username no existe.", "Atención");
        return false;
    }   
    /**
     * Valida que el username y password no esten vacidos
     * @param username
     * @param password
     * @return 
     */
    public static boolean validarUser(String username, String password){       
        if (username.equals("")) {
            successMessage("Username Incorrecto.", "Atención");
            return false;
        }
        if (password.equals("")) {
            successMessage("Password Incorrecto.", "Atención");
            return false;
        }
        return true;
    }
    /**
     * Usuarios de tipo administrador
     * @return 
     */
    private static ArrayList<User> usersAdmistrador(){
        ArrayList<User> administradores = new ArrayList<>();
        administradores.add(new User(TipoUser.ADMINISTRADOR,"admin","123456"));
        return administradores;
    }
    /**
     * Usuarios de tipo clientes
     * @return 
     */
    private static ArrayList<User> usersClientes(){
        ArrayList<User> clientes = new ArrayList<>();
        clientes.add(new User(TipoUser.CLIENTE,"customer","123456"));
        return clientes;
    }
    /**
     * Pone el usuario como visitante
     */
    public static void ponerUserInvitado(){
        Security.usuario = new User(TipoUser.VISITANTE, "VISITANTE", "");
    }
}
