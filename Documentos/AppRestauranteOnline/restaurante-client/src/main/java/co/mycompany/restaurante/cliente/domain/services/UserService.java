
package co.mycompany.restaurante.cliente.domain.services;

import co.mycompany.restaurante.cliente.access.Factory;
import co.mycompany.restaurante.cliente.domain.User;
import static co.mycompany.restaurante.cliente.infra.Messages.successMessage;
import co.mycompany.restaurante.cliente.infra.Security;

/**
 * Servicio de usuarios del sistema
 * @author Kevith Felipe Bastidas
 */
public class UserService {
    /**
     * autentica el usuario si y solo si existe el username y la contraseña
     * es correcta.
     * @param login username
     * @param password constraseña
     * @return true si inicio seccion, false si no inicio seccion
     */
    public static boolean autenticacion(String login, String password) {
        if (login.equals("customer") && password.equals("123456")) {
            Security.usuario = new User("cliente", login, password);
//            successMessage("Cliente autenticado correctamente.", "Atención");
            return true;
        }
        RestauranteService restauranteService =new RestauranteService();
        String clave= "";
        try {
            clave = restauranteService.getAdministrador(login);
        } catch (Exception e) {
        }
        if (clave.equals("null")) {
            successMessage("Username no existe.", "Atención");
        }else if(clave.equals(password)){
            Security.usuario = new User("administrador", login, password);
            successMessage("Administrador autenticado correctamente.", "Atención");
            return true;
        }else{
            successMessage("Contraseña Incorrecta.", "Atención");
        }
        return false;
    }
    
}
