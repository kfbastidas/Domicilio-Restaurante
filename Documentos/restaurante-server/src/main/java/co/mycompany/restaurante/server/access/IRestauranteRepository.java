package co.mycompany.restaurante.server.access;

import co.mycompany.restaurante.commons.domain.Administrador;
import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import java.util.ArrayList;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.commons.domain.Plato;

/**
 * Interface del respositorio de clientes
 * @author Kevith Felipe Bastidas
 */
public interface IRestauranteRepository {

    /**
     * Adiciona un componenete a la base de datos
     * @param componente
     * @return
     */
    public String addComponente(Componente componente);
    /**
     * obtiene todos los componentes registrados
     * @return 
     */
    public ArrayList<Componente> getComponentes();
    /**
     * Obtiene el menu de los componenetes en un restaurante y dia determinado
     * @param idRestaurante
     * @param dia
     * @return 
     */
    public ArrayList<Componente> getMenuComponentes(int idRestaurante,DiaSemana dia);
    /**
     * Adiciona un componente en un restuarante determinado y dia determinado
     * @param idRestaurante
     * @param componente
     * @param dia
     * @return
     */
    public String addComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia);
    /**
     * Elimina un componente de un restaurante determinado en un dia determinado
     * @param idRestaurante
     * @param componente
     * @param dia
     * @return
     */
    public String deleteComponenteSemanal(int idRestaurante,Componente componente,DiaSemana dia);
    /**
     * Actualiza el plato en un restaurante determinado
     * @param idRestaurante
     * @param plato
     * @return 
     */
    public String updatePlato(int idRestaurante,Plato plato); 
    /**
     * obtiene la lista de todos los restaurantes
     * @return 
     */
    public ArrayList<Restaurante> getRestaurantes();
    /**
     * obtiene el plato del un restaurante con id
     * @param idRestaurantes
     * @return 
     */
    public Plato getPlato(int idRestaurantes);
    /**
     * obtiene la clave del administrador en caso de existir
     * @param usuario
     * @return 
     */
    public String getAdministrador(String usuario);
    
}
