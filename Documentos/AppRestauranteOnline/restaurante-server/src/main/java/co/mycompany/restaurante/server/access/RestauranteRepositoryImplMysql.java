package co.mycompany.restaurante.server.access;

import co.mycompany.restaurante.commons.domain.Administrador;
import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.commons.domain.Plato;
import co.mycompany.restaurante.commons.domain.TipoComponente;
import co.mycompany.restaurante.commons.infra.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Repositorio de Clientes en MySWL
 *
 * @author Kevith Felipe Bastidas
 */
public class RestauranteRepositoryImplMysql implements IRestauranteRepository {

    /**
     * Conección con Mysql
     */
    private Connection conn;

    public RestauranteRepositoryImplMysql() {}
    @Override
    public ArrayList<Componente> getMenuComponentes(int idRestaurantes, DiaSemana dia) {
        ArrayList<Componente> menu = new ArrayList();
        try{
            this.connect();
            //select * from restcomponente natural join componente where rest_id=1 and dia='LUNES'
            String sql = "select * from restcomponente natural join componente where rest_id=? and dia=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Integer.toString(idRestaurantes));
            pstmt.setString(2, dia.name());
            ResultSet res = pstmt.executeQuery();
            while(res.next()) {      
                Componente componente = new Componente();
                componente.setId(Integer.parseInt(res.getString("comp_id")));
                componente.setNombre(res.getString("comp_nombre"));
                componente.setTipo(TipoComponente.valueOf(res.getString("comp_tipo")));
                menu.add(componente);
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar getMenuDia de la base de datos", ex);
        }
        return menu;
    }   
    @Override
    public ArrayList<Restaurante> getRestaurantes() {
        ArrayList<Restaurante> Restaurantes = new ArrayList();
        try{
            this.connect();
            String sql = "SELECT * from restaurante";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while(res.next()) {      
                Restaurante restaurante = new Restaurante();
                restaurante.setId(Integer.parseInt(res.getString("rest_id")));
                restaurante.setNombre(res.getString("rest_nombre"));
                restaurante.setDirecccion(res.getString("rest_direccion"));
                restaurante.setCiudad(res.getString("rest_ciudad"));
                restaurante.setTelefono(res.getString("rest_telefono"));
//                restaurante.setMenuPlatos(getMenuPlatos(restaurante.getId()));
                Restaurantes.add(restaurante);
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar Customer de la base de datos", ex);
        }
        return Restaurantes;
    }
    @Override
    public Plato getPlato(int idRestaurante) {
        Plato p = new Plato();
        try{
            this.connect();
            //select * from plato where rest_id = 1
            String sql = "select * from plato where rest_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Integer.toString(idRestaurante));
            ResultSet res = pstmt.executeQuery();
            if(res.next()) {                      
                p.setId(Integer.parseInt(res.getString("pla_id")));
                p.setDescripcion(res.getString("pla_descripcion"));
                p.setPrecio(Integer.parseInt(res.getString("pla_precio")));
                p.setCantidad(Integer.parseInt(res.getString("pla_cantidad")));
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar getMenuDia de la base de datos", ex);
        }
        return p;
    }
    @Override
    public String addComponente(Componente componente) {
        try {
            this.connect();
            int cont;
            //insert into componente values(1,'lentejas','PRINCIPIO');
            String sql = "insert into componente values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            cont = 1;
            pstmt.setInt(cont, componente.getId());
            cont++;
            pstmt.setString(cont, componente.getNombre());
            cont++;
            pstmt.setString(cont, componente.getTipo().name());
            pstmt.executeUpdate();    
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al insertar el registro", ex);
            return "Error, el componente con ese id y nombre ya existe";
        }
        return "Componente añadido correctamente";
    }
    @Override
    public String addComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        try {
            this.connect();
            int cont;
            //insert into restcomponente values(1,1,'lentejas','LUNES');
            String sql = "insert into restcomponente values(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            cont = 1;
            pstmt.setInt(cont, idRestaurante);
            cont++;
            pstmt.setInt(cont, componente.getId());
            cont++;
            pstmt.setString(cont, componente.getNombre());
            cont++;
            pstmt.setString(cont, dia.name());
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al insertar el registro", ex);
        }
        return "Menu Componente añadido correctamente";
    }
    @Override
    public String updatePlato(int idRestaurante,Plato plato){
        try {
            this.connect();
            
            String sql = "select count(*) cont from plato where rest_id=?";
            PreparedStatement pstmt2 = conn.prepareStatement(sql);
            pstmt2.setString(1, Integer.toString(idRestaurante));
            ResultSet res = pstmt2.executeQuery();
            int numPlato = 0;
            if(res.next()) {               
                numPlato = Integer.parseInt(res.getString("cont"));
            }
            if (numPlato > 0) {
                //update plato set pla_descripcion="",pla_precio=1,pla_cantidad=3 where pla_id = 1 and rest_id=1
                sql = "update plato set pla_descripcion=?,pla_precio=?,pla_cantidad=? where pla_id = ? and rest_id=?";
            }else{
                //insert into plato values(1,'Es una chuleta de pollo grande',6000,5,1)
                sql = "insert into plato values(1,?,?,?,?)";
            }
            int cont;
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            cont = 1;
            pstmt.setString(cont++, plato.getDescripcion());
            pstmt.setInt(cont++, plato.getPrecio());
            pstmt.setInt(cont++, plato.getCantidad());
            if (numPlato>0) {
                pstmt.setInt(cont++, plato.getId());
            }
            pstmt.setInt(cont++, idRestaurante);
            pstmt.executeUpdate();    
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al insertar el registro", ex);
        }
        return "Plato update correctamente";
    }
    @Override
    public String deleteComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        try {
            this.connect();
            int cont;
            //insert into restcomponente values(1,1,'lentejas','LUNES');
            String sql = "delete from restcomponente where rest_id=? and comp_id=? and comp_nombre=? and dia=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            cont = 1;
            pstmt.setInt(cont, idRestaurante);
            cont++;
            pstmt.setInt(cont, componente.getId());
            cont++;
            pstmt.setString(cont, componente.getNombre());
            cont++;
            pstmt.setString(cont, dia.name());
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al insertar el registro", ex);
        }
        return "Menu Componente se elimino correctamente";
    }
    @Override
    public String getAdministrador(String usuario) {
        String clave = null;
        try{
            this.connect();
            String sql = "select * from administrador where adm_usuario=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);
            ResultSet res = pstmt.executeQuery();
            while(res.next()) {      
                clave = res.getString("adm_clave");
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar getMenuDia de la base de datos", ex);
        }
        return clave;
    }
    @Override
    public ArrayList<Componente> getComponentes(){
        ArrayList<Componente> componentes = new ArrayList<>();
        try{
            this.connect();
            //select * from componente
            String sql = "select * from componente";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while(res.next()) {      
                Componente c = new Componente();
                c.setId(Integer.parseInt(res.getString("comp_id")));
                c.setNombre(res.getString("comp_nombre"));
                c.setTipo(TipoComponente.valueOf(res.getString("comp_tipo")));
                componentes.add(c);
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar getComponentes de la base de datos", ex);
        }
        return componentes;
    }    
    /**
     * Permite hacer la conexion con la base de datos
     *
     * @return
     */
    public int connect() {
        try {
            Class.forName(Utilities.loadProperty("server.db.driver"));
            //crea una instancia de la controlador de la base de datos
            String url = Utilities.loadProperty("server.db.url");
            String username = Utilities.loadProperty("server.db.username");
            String pwd = Utilities.loadProperty("server.db.password");
            conn = DriverManager.getConnection(url, username, pwd);
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.SEVERE, "Error al consultar Customer de la base de datos", ex);
        }
        return -1;
    }
    /**
     * Cierra la conexion con la base de datos
     *
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteRepositoryImplMysql.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
        }
    }

}
