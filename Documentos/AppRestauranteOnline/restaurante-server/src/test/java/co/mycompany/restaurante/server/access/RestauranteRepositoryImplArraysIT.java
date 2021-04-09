/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.server.access;

import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Plato;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.commons.domain.TipoComponente;
import co.mycompany.restaurante.server.domain.services.RestauranteService;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kevit
 */
public class RestauranteRepositoryImplArraysIT {
    
    public RestauranteRepositoryImplArraysIT() {
    }
    

    /**
     * Test of addComponente method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testAddComponente() {
        System.out.println("addComponente");
        //componente no existente
        Componente componente = new Componente(8, "oyucos", TipoComponente.PRINCIPIO);
        
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        
        String expResult = "Componente añadido correctamente";
        String result = instance.addComponente(componente);
        assertEquals(expResult, result);
        
        //componente existente que seria el mismo
        Componente componente2 = componente;
        expResult = "Error, el componente con ese id y nombre ya existe";
        result = instance.addComponente(componente2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getComponentes method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testGetComponentes() {
        System.out.println("getComponentes");
        
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        
        int expResult = 17;
        ArrayList<Componente> result = instance.getComponentes();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getMenuComponentes method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testGetMenuComponentes() {
        System.out.println("getMenuComponentes del restaurante con id = 1 y diasemana = LUNES");
        int idRestaurante = 1;
        DiaSemana dia = DiaSemana.LUNES;
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        int expResult = 17;
        ArrayList<Componente> result = instance.getMenuComponentes(idRestaurante, dia);
        assertEquals(expResult, result.size());
    }
    /**
     * Test of getPlato method, of class RestauranteRepositoryImplArrays.
     */
     @Test
    public void testGetPlato() {
        System.out.println("getPlato del restaurante con id = 1");
        int idRestaurantes = 1;
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        Plato expResult = new Plato(idRestaurantes, "descripcion del plato", 0,0);
        Plato result = instance.getPlato(idRestaurantes);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getCantidad(), result.getCantidad());
        assertEquals(expResult.getDescripcion(), result.getDescripcion());
    }

    /**
     * Test of addComponenteSemanal method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testAddComponenteSemanal() {
        System.out.println("addComponenteSemanal al restaurante 2");
        int idRestaurante = 2;
        Componente componente = new Componente(1,"lentejas", TipoComponente.PRINCIPIO);
        DiaSemana dia = DiaSemana.LUNES;
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        String expResult = "Menu Componente añadido correctamente";
        String result = instance.addComponenteSemanal(idRestaurante, componente, dia);
        assertEquals(expResult, result);
        
        
        //caso donde agrego el mismo componente
        expResult = "Error el componente ya existe";
        result = instance.addComponenteSemanal(idRestaurante, componente, dia);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteComponenteSemanal method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testDeleteComponenteSemanal() {
        System.out.println("deleteComponenteSemanal");
        int idRestaurante = 1;
        Componente componente = new Componente(1,"lentejas", TipoComponente.PRINCIPIO);
        DiaSemana dia = DiaSemana.LUNES;
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        String expResult = "Menu Componente se elimino correctamente";
        String result = instance.deleteComponenteSemanal(idRestaurante, componente, dia);
        assertEquals(expResult, result);
        
        int expResult2 = 16;
        ArrayList<Componente> result2 = instance.getMenuComponentes(idRestaurante, dia);
        assertEquals(expResult2, result2.size());
    }

    /**
     * Test of updatePlato method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testUpdatePlato() {
        System.out.println("updatePlato");
        int idRestaurante = 1;
        Plato plato = new Plato(1, "descripcion nueva", 0, 0);
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        String expResult = "Plato update correctamente";
        
        Plato plato1 = instance.getPlato(idRestaurante);
        assertEquals("descripcion del plato", plato1.getDescripcion());
        
        String result = instance.updatePlato(idRestaurante, plato);
        assertEquals(expResult, result);
        
        plato1 = instance.getPlato(idRestaurante);
        assertEquals("descripcion nueva", plato1.getDescripcion());
    }

    /**
     * Test of getRestaurantes method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testGetRestaurantes() {
        System.out.println("getRestaurantes");
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        int expResult = 4;
        ArrayList<Restaurante> result = instance.getRestaurantes();
        assertEquals(expResult, result.size());
        System.out.println(result);
    }

    
   
    /**
     * Test of getAdministrador method, of class RestauranteRepositoryImplArrays.
     */
    @Test
    public void testGetAdministrador() {
        System.out.println("getAdministrador");
        String usuario = "admin";
        RestauranteRepositoryImplArrays instance = new RestauranteRepositoryImplArrays();
        String expResult = "123456";
        String result = instance.getAdministrador(usuario);
        assertEquals(expResult, result);
        
        result = instance.getAdministrador("");
        assertEquals("null", result);
    }
    
}
