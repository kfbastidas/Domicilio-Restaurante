package co.mycompany.restaurante.server.domain.services;

import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Restaurante;
import java.util.ArrayList;
import co.mycompany.restaurante.commons.domain.Plato;
import co.mycompany.restaurante.server.access.IRestauranteRepository;

/**
 * Servicio de clientes. Da acceso a la lógica de negocio
 *
 * @author Kevith Felipe Bastidas
 */
public class RestauranteService {

    /**
     * Repositorio de clientes
     */
    IRestauranteRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IRestauranteRepository
     */
    public RestauranteService(IRestauranteRepository repo) {
        this.repo = repo;
    }
    
    public ArrayList<Componente> getMenuComponentes(int idRestaurantes,DiaSemana dia){
        return repo.getMenuComponentes(idRestaurantes,dia);
    }

    public ArrayList<Restaurante> getRestaurantes(){
        return repo.getRestaurantes();
    }
    
    public String addComponente(Componente componente){
        return repo.addComponente(componente);
    }
    
    public ArrayList<Componente> getComponentes(){
        return repo.getComponentes();
    }
    
    public String getAdministrador(String usuario){
        return repo.getAdministrador(usuario);
    }
    
    public Plato getPlato(int idRestaurante){
        return repo.getPlato(idRestaurante);
    }
    
    public String addComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        return repo.addComponenteSemanal(idRestaurante, componente,dia);
    }
    
    public String deleteComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        return repo.deleteComponenteSemanal(idRestaurante, componente,dia);
    }
    
    public String updatePlato(int idRestaurante,Plato plato){
        return repo.updatePlato(idRestaurante, plato);
    }
}
