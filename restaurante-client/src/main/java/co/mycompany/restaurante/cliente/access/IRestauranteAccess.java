package co.mycompany.restaurante.cliente.access;

import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Plato;
import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface que define los servicios de persistencia de Clientes de la agencia
 *
 * @author Kevith Felipe Bastidas
 */
public interface IRestauranteAccess {

    /**
     * Adiciona un componenete a la base de datos
     * @param componente
     * @return
     */
    public String addComponente(Componente componente);
    /**
     * Elimina un componente de la base de datos.
     * @param idComponente
     * @return 
     */
    public String deleteComponente(int idComponente);
    /**
     * obtiene todos los componentes registrados
     * @return 
     */
    public ArrayList<Componente> getComponentes();
    /**
     * obtiene un componente de un id determinado.
     * @param idComponente
     * @return 
     */
    public Componente getComponente(int idComponente);
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
     * @param dia
     * @param plato
     * @return 
     */
    public String updatePlato(int idRestaurante,DiaSemana dia,Plato plato); 
    /**
     * obtiene la lista de todos los restaurantes
     * @return 
     */
    public List<Restaurante> getRestaurantes();
    /**
     * obtiene un resturante buscado por el id
     * @param idRestaurante
     * @return 
     */
    public Restaurante getRestaurante(int idRestaurante);
    /**
     * Elimina la informacion  de un restaurante
     * @param idRestaurante
     * @return 
     */
    public String deleteRestaurante(int idRestaurante);
    /**
     * Adiciona la informacion de un restaurante.
     * @param restaurante
     * @return 
     */
    public String addRestaurante(Restaurante restaurante);
    /**
     * Elimina la informacion del plato de un restaurante en un dia determinado.
     * @param idRestaurante
     * @param dia
     * @return 
     */
    public String deletePlato(int idRestaurante, DiaSemana dia);
    
    /**
     * obtiene el plato del un restaurante con id
     * @param idRestaurante
     * @param dia
     * @return 
     */
    public Plato getPlato(int idRestaurante, DiaSemana dia);
    /**
     * Adiciona la informacion del plato de un restaurante en un dia determinado.
     * @param plato
     * @return 
     */
    public String addPlato(Plato plato);
    /**
     * obtiene la clave del administrador en caso de existir
     * @param usuario
     * @return 
     */
    public String getAdministrador(String usuario);
}
