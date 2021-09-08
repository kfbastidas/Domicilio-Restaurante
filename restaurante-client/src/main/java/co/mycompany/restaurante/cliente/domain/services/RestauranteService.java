package co.mycompany.restaurante.cliente.domain.services;

import co.mycompany.restaurante.cliente.access.Factory;
import co.mycompany.restaurante.cliente.access.IRestauranteAccess;
import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Plato;
import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
import java.util.ArrayList;
import java.util.List;

/**
 * Es una fachada para comunicar la presentación con el
 * dominio
 *
 * @author Kevith Feipe Bastidas
 */
public class RestauranteService{

    private final IRestauranteAccess service;

    /**
     * Constructor privado que evita que otros objetos instancien
     */
    public RestauranteService() {
        this.service = Factory.getInstance().getRestauranteService();
    }

    /**
     * Adiciona un componenete a la base de datos
     * @param componente
     * @return
     */
    public String addComponente(Componente componente){
        return service.addComponente(componente);
    }
    /**
     * obtiene todos los componentes registrados
     * @return 
     */
    public ArrayList<Componente> getComponentes(){
        return service.getComponentes();
    }   
    /**
     * Obtiene el menu de los componenetes en un restaurante y dia determinado
     * @param idRestaurante
     * @param dia
     * @return 
     */
    public ArrayList<Componente> getMenuComponentes(int idRestaurante,DiaSemana dia){
        return service.getMenuComponentes(idRestaurante, dia);
    }
    /**
     * Adiciona un componente en un restuarante determinado y dia determinado
     * @param idRestaurante
     * @param componente
     * @param dia
     * @return
     */
    public String addComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        return service.addComponenteSemanal(idRestaurante, componente,dia);
    }
    /**
     * Elimina un componente de un restaurante determinado en un dia determinado
     * @param idRestaurante
     * @param componente
     * @param dia
     * @return
     */
    public String deleteComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia){
        return service.deleteComponenteSemanal(idRestaurante, componente,dia);
    }
    /**
     * Actualiza el plato en un restaurante determinado
     * @param idRestaurante
     * @param dia
     * @param plato
     * @return 
     */
    public String updatePlato(int idRestaurante,DiaSemana dia,Plato plato){
        return service.updatePlato(idRestaurante,dia, plato);
    }  
    /**
     * obtiene la lista de todos los restaurantes
     * @return 
     */
    public List<Restaurante> getRestaurantes(){ 
        
        return service.getRestaurantes();
    }
    /**
     * obtiene el plato del un restaurante con id
     * @param idRestaurante
     * @param dia
     * @return 
     */
    public Plato getPlato(int idRestaurante,DiaSemana dia){
        return service.getPlato(idRestaurante,dia);
    }
    /**
     * obtiene la clave del administrador en caso de existir
     * @param usuario
     * @return 
     */
    public String getAdministrador(String usuario){
        return service.getAdministrador(usuario);
    }
}
