package co.mycompany.restaurante.cliente.domain.services;

import co.mycompany.restaurante.cliente.access.Factory;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.cliente.access.IRestauranteAccess;
import co.mycompany.restaurante.cliente.infra.Subject;
import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Plato;
import java.util.ArrayList;

/**
 * Es una fachada para comunicar la presentación con el
 * dominio
 *
 * @author Kevith Feipe Bastidas
 */
public class RestauranteService extends Subject{

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
     * @param plato
     * @return 
     */
    public String updatePlato(int idRestaurante,Plato plato){
        super.notifyAllObserves(idRestaurante);
        return service.updatePlato(idRestaurante, plato);
    }  
    /**
     * obtiene la lista de todos los restaurantes
     * @return 
     */
    public ArrayList<Restaurante> getRestaurantes(){ 
        return service.getRestaurantes();
    }
    /**
     * obtiene el plato del un restaurante con id
     * @param idRestaurantes
     * @return 
     */
    public Plato getPlato(int idRestaurantes){
        return service.getPlato(idRestaurantes);
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
