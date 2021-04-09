package co.mycompany.restaurante.server.access;

import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.commons.domain.Plato;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de IRestauranteRepository. Utilliza arreglos en memoria
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public final class RestauranteRepositoryImplArrays implements IRestauranteRepository {

    /**
     * Array List de clientes
     */
    private static List<Restaurante> restaurante;

    public RestauranteRepositoryImplArrays() {
        if (restaurante == null){
            restaurante = new ArrayList();
        }
        
        if (restaurante.size() == 0){
            inicializar();
        }
    }

    public void inicializar() {
//        ArrayList <Plato> listaPlatos = new ArrayList<Plato>(); 
//        Restaurante regRestaurante = new Restaurante(10,"Cra 12 a ","HOLA",100);
//        listaPlatos.add(new Plato(10000,"300 g de nada","Nada"));
//        listaPlatos.add(new Plato(11000,"222 g de nada","Nada2"));
//        regRestaurante.setAtrMenuEspecial(listaPlatos);
//        restaurante.add(regRestaurante);
//        
//        Restaurante regRestaurante2 = new Restaurante(9,"Cra 9 a ","CHAO",120);
//        listaPlatos.add(new Plato(10000,"400 g de todo","todo"));
//        listaPlatos.add(new Plato(11000,"222 g de todo","todo2"));
//        regRestaurante2.setAtrMenuEspecial(listaPlatos);
//        restaurante.add(regRestaurante2);
    }

    @Override
    public String addComponente(Componente componente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Componente> getComponentes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Componente> getMenuComponentes(int idRestaurante, DiaSemana dia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addComponenteSemanal(int idRestaurante, Componente componente, DiaSemana dia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteComponenteSemanal(int idRestaurante, Componente componente, DiaSemana dia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updatePlato(int idRestaurante, Plato plato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Restaurante> getRestaurantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plato getPlato(int idRestaurantes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAdministrador(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
