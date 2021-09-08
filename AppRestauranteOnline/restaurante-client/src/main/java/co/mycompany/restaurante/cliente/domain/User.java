package co.mycompany.restaurante.cliente.domain;

/**
 * Representa un usuario que entra al sistema
 *
 * @author Kevith Felipe Bastidas
 */
public class User {

    /**
     * Tipo de Usuario
     */
    private TipoUser login;
    /**
     * Contraseña
     */
    private String password;
    /**
     * Nombres y apellidos completos
     */
    private String username;

    /**
     * Constructor que inicializa un usuario
     *
     * @param login nombre del usuario
     * @param password contraseña secreta
     * @param username nombre del usuario
     */
    public User(TipoUser login, String username, String password) {
        this.login = login;
        this.password = password;
        this.username = username;
    }

    
    public TipoUser getLogin() {
        return login;
    }

    public void setLogin(TipoUser login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contrasenia) {
        this.password = contrasenia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nombres) {
        this.username = nombres;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + ", username=" + username + '}';
    }

}
