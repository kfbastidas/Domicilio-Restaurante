package co.mycompany.restaurante.cliente.access;

import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Plato;
import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
import co.mycompany.restaurante.cliente.infra.ClientServiceComponente;
import co.mycompany.restaurante.cliente.infra.ClientServicePlato;
import co.mycompany.restaurante.cliente.infra.ClientServiceRestComp;
import co.mycompany.restaurante.cliente.infra.ClientServiceRestaurante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevith Felipe Bastidas
 */
public class RestauranteAccessImplJersey implements IRestauranteAccess{
    /**
     * Atributo: instacia la clase ClientServicePlato
     */
    private ClientServicePlato clientPlato;
    /**
     * Atributo: instacia la clase clientComponente;
     */
    private ClientServiceComponente clientComponente;
    /**
     * Atributo: instancia la clase clientComponente
     */
    private ClientServiceRestaurante clientResturante;
    /**
     * Atributo: instancia la clase clientRestaurante
     */
    private ClientServiceRestComp clientRestComp;
    /**
     * Contructor por defecto de RestauranteAcessImplJersey
     */
    public RestauranteAccessImplJersey() {
        this.clientPlato = new ClientServicePlato();
        this.clientComponente = new ClientServiceComponente();
        this.clientResturante = new ClientServiceRestaurante();
        this.clientRestComp = new ClientServiceRestComp();
    }
     /**
     * Adiciona la informacion del plato de un restaurante en un dia determinado.
     * @param plato
     * @return 
     */
    @Override
    public String addPlato(Plato plato){
        return clientPlato.addPlato(plato);
    }
    /**
     * Elimina la informacion del plato de un restaurante en un dia determinado.
     * @param idRestaurante
     * @param dia
     * @return 
     */
    @Override
    public String deletePlato(int idRestaurante, DiaSemana dia){
        return clientPlato.deletePlato(idRestaurante, dia);
    }   
    /**
     * Adiciona un componenete a la base de datos
     * @param componente
     * @return
     */
    @Override
    public String addComponente(Componente componente) {
        return clientComponente.createComponente(componente);
    }
    /**
     * Elimina un componente de la base de datos.
     * @param idComponente
     * @return 
     */
    @Override
    public String deleteComponente(int idComponente){
        return clientComponente.deleteComponente(idComponente);
    }   
    /**
     * obtiene un componente de un id determinado.
     * @param idComponente
     * @return 
     */
    @Override
    public Componente getComponente(int idComponente){
        return clientComponente.getComponente(idComponente);
    }
    /**
     * obtiene todos los componentes registrados
     * @return 
     */
    @Override
    public ArrayList<Componente> getComponentes() {
        ArrayList<Componente> componentes = new ArrayList<>();
        List<Componente> componentes2 = this.clientComponente.findAllComponentes();
        for (Componente componente : componentes2) {
            componentes.add(componente);
        }
        return componentes;
    }
    /**
     * Obtiene el menu de los componenetes en un restaurante y dia determinado
     * @param idRestaurante
     * @param dia
     * @return 
     */
    @Override
    public ArrayList<Componente> getMenuComponentes(int idRestaurante, DiaSemana dia) {
        ArrayList<Componente> componentes = new ArrayList<>();
        List<Componente> componentes2 = this.clientRestComp.getMenuComponentes(idRestaurante,dia);
        for (Componente componente : componentes2) {
            componentes.add(componente);
        }
        return componentes;
    }
    /**
     * Adiciona un componente en un restuarante determinado y dia determinado
     * @param idRestaurante
     * @param componente
     * @param dia
     * @return
     */
    @Override
    public String addComponenteSemanal(int idRestaurante, Componente componente, DiaSemana dia) {
        return this.clientRestComp.addComponenteSemanal(idRestaurante, componente, dia);
    }
    /**
     * Elimina un componente de un restaurante determinado en un dia determinado
     * @param idRestaurante
     * @param componente
     * @param dia
     * @return
     */
    @Override
    public String deleteComponenteSemanal(int idRestaurante, Componente componente, DiaSemana dia) {
        return this.clientRestComp.deleteComponenteSemanal(idRestaurante, componente.getId(), dia);
    }
    /**
     * Actualiza el plato en un restaurante determinado
     * @param idRestaurante
     * @param dia
     * @param plato
     * @return 
     */
    @Override
    public String updatePlato(int idRestaurante,DiaSemana dia, Plato plato) {
        return clientPlato.updatePlato(idRestaurante, dia, plato);
    }
    /**
     * obtiene la lista de todos los restaurantes
     * @return 
     */
    @Override
    public List<Restaurante> getRestaurantes() {
        List<Restaurante> res = clientResturante.findAllRestaurantes();
        return res;
    }
    /**
     * obtiene un resturante buscado por el id
     * @param idRestaurante
     * @return 
     */
    @Override
    public Restaurante getRestaurante(int idRestaurante){
        return clientResturante.getRestaurante(idRestaurante);
    }    
    /**
     * Elimina la informacion  de un restaurante
     * @param idRestaurante
     * @return 
     */
    @Override
    public String deleteRestaurante(int idRestaurante){
        return clientResturante.deleteRestaurante(idRestaurante);
    }    
    /**
     * Adiciona la informacion de un restaurante.
     * @param restaurante
     * @return 
     */
    @Override
    public String addRestaurante(Restaurante restaurante){
        return clientResturante.addRestaurante(restaurante);
    }
    /**
     * obtiene el plato del un restaurante con id
     * @param idRestaurante
     * @param dia
     * @return 
     */
    @Override
    public Plato getPlato(int idRestaurante,DiaSemana dia) {
        Plato plato = clientPlato.getPlato(idRestaurante, dia);
        if(plato==null){
            plato = new Plato(0, "", 0, 0,"", dia, idRestaurante);
            this.clientPlato.addPlato(plato);
            return getPlato(idRestaurante,dia);
        }
        return plato;
    }
    /**
     * obtiene la clave del administrador en caso de existir
     * @param usuario
     * @return 
     */
    @Override
    public String getAdministrador(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
