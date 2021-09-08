package co.mycompany.restaurante.server.access;

import co.mycompany.restaurante.cliente.access.Factory;
import co.mycompany.restaurante.cliente.access.IRestauranteAccess;
import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Plato;
import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
import co.mycompany.restaurante.cliente.domain.entity.TipoComponente;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kevith Felipe Bastidas
 */
public class RestauranteRepositoryImplJersey {
    
    /**
     * Test añade un componente que no existe
     */
    @Test
    public void testAddComponenteNoExistente() {
        System.out.println("addComponenteNoExistente");
        
        int idComponente = 1;
        
        //instancia el servicio
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        //elimina el componente si existe para que el componente agregar no este en la base de datos
        Componente componenteEncontrado = service.getComponente(idComponente);
        if (componenteEncontrado!=null) {
            service.deleteComponente(idComponente);
        }
        
        //componente no existente
        Componente componenteSave = new Componente(idComponente, "oyucos prueba", TipoComponente.PRINCIPIO);

        String expResult = "Componente añadido correctamente";
        String result = service.addComponente(componenteSave);
        assertEquals(expResult, result);
        
        //agrega el componente que existia antes de hacer la prueba
        if (componenteEncontrado!=null) {
            service.deleteComponente(idComponente);
            service.addComponente(componenteEncontrado);
        }
    }
    
    /**
     * Test añade un componente ya existente
     */
    @Test
    public void testAddComponenteExistente() {
        System.out.println("addComponenteExistente");
        int idComponente = 7777; 
        //instancia el servicio
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        //agrega el componente en caso de no existir anteriormente
        Componente componenteEncontrado = service.getComponente(idComponente);
        if (componenteEncontrado==null) {
            service.addComponente(new Componente(idComponente,"lentejas",TipoComponente.PRINCIPIO));
        }
        
        //componente existentente
        Componente componente = new Componente(idComponente, "lentejas", TipoComponente.PRINCIPIO);

        String expResult = "Error, el componente con ese id y nombre ya existe";
        String result = service.addComponente(componente);
        assertEquals(expResult, result);
        
        if (componenteEncontrado==null) {
            service.deleteComponente(idComponente);
        }
        
    }
    
    /**
     * Test obtiene todos los componentes
     */
    @Test
    public void testGetComponentes() {
        System.out.println("getComponentes");
        
        //instancia el servicio
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        int expResult = 17;
        List<Componente> result = service.getComponentes();
        assertTrue(result.size()>=expResult);
    }
    
    /**
     * Test obtiene un componente existente
     */
    @Test
    public void testGetComponenteExistente() {
        System.out.println("getComponenteExistente");
        int idComponente = 2;
        Componente componenteComparar = new Componente(idComponente,"jugo de naranja prueba",TipoComponente.BEBIDA);
        
        //instancia el servicio
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        //agrega el componente en caso de no existir anteriormente
        Componente componenteEncontrado = service.getComponente(idComponente);
        if (componenteEncontrado!=null) {
            service.deleteComponente(idComponente);
        }
        service.addComponente(componenteComparar);
        
        //Encuentra el componente existente
        Componente componente = service.getComponente(idComponente);
        
        assertEquals(componente.getId(), componenteComparar.getId());
        assertEquals(componente.getNombre(), componenteComparar.getNombre());
        assertEquals(componente.getTipo(), componenteComparar.getTipo());
        
        //agrega el componente anterior en caso de existir
        if (componenteEncontrado!=null) {
            service.deleteComponente(idComponente);
            service.addComponente(componenteEncontrado);
        }
    }
    
    /**
     * Test obtiene un componente no existente
     */
    @Test
    public void testGetComponenteNoExistente() {
        System.out.println("getComponenteNoExistente");
        
        //instancia el servicio
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        
        Componente componente = service.getComponente(100);
        
        assertEquals(componente, null);        
    }
       
    /**
     * Test obtiene los componentes del dia Lunes y restaurante con id 1
     */
    @Test
    public void testGetMenuComponentes() {
        System.out.println("getMenuComponentes del restaurante con id = 1 y diasemana = LUNES");
        int idRestaurante = 1;
        DiaSemana dia = DiaSemana.LUNES;
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        int expResult = 17;
        ArrayList<Componente> result = service.getMenuComponentes(idRestaurante, dia);
        assertTrue(result.size()>=expResult);
    }
    
    /**
     * Test agrega componentes en un dia de la semana de un determinado restaurante
     */
    @Test
    public void testAddMenuComponentes() {
        System.out.println("addMenuComponentes del restaurante con id = 2 y diasemana = JUEVES");
        int idRestaurante = 2;
        DiaSemana dia = DiaSemana.VIERNES;
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        ArrayList<Componente> componentesEncontrados = service.getMenuComponentes(idRestaurante, dia);
        
        if (!componentesEncontrados.isEmpty()) {
            for(Componente c: componentesEncontrados){
                String mensaje = service.deleteComponenteSemanal(idRestaurante, c, dia);
                assertEquals(mensaje, "ComponenteSemanal eliminado correctamente");
            }
        }
        
        ArrayList<Componente> componentesSave = service.getComponentes();
        if (!componentesSave.isEmpty()) {
            for(Componente c: componentesSave){
                String mensaje = service.addComponenteSemanal(idRestaurante, c, dia);
                assertEquals(mensaje, "ComponenteSemanal añadido correctamente");
            }
        }
        
        ArrayList<Componente> componentesEsperados = service.getMenuComponentes(idRestaurante, dia);
        int expResult = componentesEsperados.size();
        assertEquals(expResult, componentesSave.size());
        
        if (!componentesEsperados.isEmpty()) {
            for(Componente c: componentesSave){
                String mensaje = service.deleteComponenteSemanal(idRestaurante, c, dia);
                assertEquals(mensaje, "ComponenteSemanal eliminado correctamente");
            }
        }
        
        if (!componentesEncontrados.isEmpty()) {
            for(Componente c: componentesEncontrados){
                String mensaje = service.addComponenteSemanal(idRestaurante, c, dia);
                assertEquals(mensaje, "ComponenteSemanal añadido correctamente");
            }
        }
        
    }
    
    /**
     * Test Obtiene la informacion de un plato de un restaurante y dia determinado
     */
    @Test
    public void testGetPlato() {
        System.out.println("getPlato del restaurante con id = 1 y diasemana = LUNES");
        int idRestaurante = 1;
        DiaSemana dia = DiaSemana.LUNES;
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        Plato platoEncontrado = service.getPlato(idRestaurante, dia);
        if(platoEncontrado!=null){
            String mensaje = service.deletePlato(idRestaurante, dia);
            assertEquals(mensaje,"Plato eliminado correctamente");
        }
        
        Plato platoSave = new Plato(0,"Descripcion prueba", 7000, 5,"url de la imagen del plato.jpg", dia, idRestaurante);
        assertEquals(service.addPlato(platoSave),"Plato añadido correctamente");
        
        Plato platoEsperado = service.getPlato(idRestaurante, dia);      
        assertEquals(platoSave.getDescripcion(),platoEsperado.getDescripcion());
        assertEquals(platoSave.getCantidad(),platoEsperado.getCantidad());
        assertEquals(platoSave.getPrecio(),platoEsperado.getPrecio());
        assertEquals(platoSave.getUrl_imagen(),platoEsperado.getUrl_imagen());
        assertEquals(platoSave.getRest_id(),platoEsperado.getRest_id());
        assertEquals(platoSave.getDia(),platoEsperado.getDia());
        
        if(platoEncontrado!=null){
            String mensaje = service.deletePlato(platoSave.getRest_id(), platoSave.getDia());
            assertEquals(mensaje,"Plato eliminado correctamente");
            
            assertEquals(service.addPlato(platoEncontrado),"Plato añadido correctamente");
        }
        
    }
    
    /**
     * Test Actualiza la informacion de un plato de un restaurante y dia determinado
     */
    @Test
    public void testUpdatePlatoNoExistente() {
        System.out.println("UpdatePlatoNoExistente del restaurante con id = 3 y diasemana = VIERNES");
        int idRestaurante = 5;
        DiaSemana dia = DiaSemana.VIERNES;
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        Plato platoEncontrado = service.getPlato(idRestaurante, dia);
        if(platoEncontrado!=null){
            String mensaje = service.deletePlato(idRestaurante, dia);
            assertEquals(mensaje,"Plato eliminado correctamente");
        }
        
        Plato platoSave = new Plato(0,"Descripcion prueba", 7000, 5,"url de la imagen del plato.jpg", dia, idRestaurante);
        assertEquals(service.addPlato(platoSave),"Plato añadido correctamente");
        
        Plato platoEsperado = service.getPlato(idRestaurante, dia);      
        assertEquals(platoSave.getDescripcion(),platoEsperado.getDescripcion());
        assertEquals(platoSave.getCantidad(),platoEsperado.getCantidad());
        assertEquals(platoSave.getPrecio(),platoEsperado.getPrecio());
        assertEquals(platoSave.getUrl_imagen(),platoEsperado.getUrl_imagen());
        assertEquals(platoSave.getRest_id(),platoEsperado.getRest_id());
        assertEquals(platoSave.getDia(),platoEsperado.getDia());
        
        if(platoEncontrado!=null){
            String mensaje = service.deletePlato(platoSave.getRest_id(), platoSave.getDia());
            assertEquals(mensaje,"Plato eliminado correctamente");
            
            assertEquals(service.addPlato(platoEncontrado),"Plato añadido correctamente");
        }
        
    }
    
    
    /**
     * Test Actualiza la informacion de un plato de un restaurante y dia determinado
     */
    @Test
    public void testUpdatePlatoExistente() {
        System.out.println("UpdatePlatoExistente del restaurante con id = 1 y diasemana = LUNES");
        int idRestaurante = 1;
        DiaSemana dia = DiaSemana.LUNES;
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        Plato platoEncontrado = service.getPlato(idRestaurante, dia);
        
        if (platoEncontrado != null) {
            Plato platoUpdate = new Plato(0, "Descripcion prueba", 7000, 0, "url de la imagen del plato.jpg", dia, idRestaurante);

            System.out.println(service.updatePlato(idRestaurante, dia, platoUpdate));

            Plato platoEsperado = service.getPlato(idRestaurante, dia);
            assertEquals(platoUpdate.getDescripcion(), platoEsperado.getDescripcion());
            assertEquals(platoUpdate.getCantidad(), platoEsperado.getCantidad());
            assertEquals(platoUpdate.getPrecio(), platoEsperado.getPrecio());
            assertEquals(platoUpdate.getUrl_imagen(), platoEsperado.getUrl_imagen());
            assertEquals(platoUpdate.getRest_id(), platoEsperado.getRest_id());
            assertEquals(platoUpdate.getDia(), platoEsperado.getDia());

            System.out.println(service.updatePlato(idRestaurante, dia, platoEncontrado));
        }
    }
    
    /**
     * Test Agrega la informacion de un plato de un restaurante y dia determinado
     */
    @Test
    public void testAddPlatoExistente() {
        System.out.println("AddPlatoExistente del restaurante con id = 1 y diasemana = LUNES");
        int idRestaurante = 1;
        DiaSemana dia = DiaSemana.LUNES;
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        Plato platoEncontrado = service.getPlato(idRestaurante, dia);
        
        if (platoEncontrado != null) {
            Plato platoCreate = new Plato(0, "Descripcion prueba", 7000, 0, "url de la imagen del plato.jpg", dia, idRestaurante);

            assertEquals("Error, el plato con ese idRest y dia ya existe", service.addPlato(platoCreate));
        }
    }
    
    /**
     * Test Agrega la informacion de un plato de un restaurante y dia determinado
     */
    @Test
    public void testAddPlatoNoExistente() {
        System.out.println("AddPlatoNoExistente del restaurante con id = 3 y diasemana = VIERNES");
        int idRestaurante = 5;
        DiaSemana dia = DiaSemana.VIERNES;
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        Plato platoEncontrado = service.getPlato(idRestaurante, dia);
        if(platoEncontrado!=null){
            String mensaje = service.deletePlato(idRestaurante, dia);
            assertEquals(mensaje,"Plato eliminado correctamente");
        }
        
        Plato platoSave = new Plato(0,"Descripcion prueba", 7000, 5,"url de la imagen del plato.jpg", dia, idRestaurante);
        assertEquals(service.addPlato(platoSave),"Plato añadido correctamente");
        
        Plato platoEsperado = service.getPlato(idRestaurante, dia);      
        assertEquals(platoSave.getDescripcion(),platoEsperado.getDescripcion());
        assertEquals(platoSave.getCantidad(),platoEsperado.getCantidad());
        assertEquals(platoSave.getPrecio(),platoEsperado.getPrecio());
        assertEquals(platoSave.getUrl_imagen(),platoEsperado.getUrl_imagen());
        assertEquals(platoSave.getRest_id(),platoEsperado.getRest_id());
        assertEquals(platoSave.getDia(),platoEsperado.getDia());
        
        if(platoEncontrado!=null){
            String mensaje = service.deletePlato(platoSave.getRest_id(), platoSave.getDia());
            assertEquals(mensaje,"Plato eliminado correctamente");
            
            assertEquals(service.addPlato(platoEncontrado),"Plato añadido correctamente");
        }
        
    }
    
    /**
     * Test Agrega la informacion de un restaurante
     */
    @Test
    public void testAddRestauranteNoExistente() {
        System.out.println("AddRestauranteNoExistente");
        int idRestaurante = 5;
        
        Restaurante restauranteAgregar = new Restaurante(idRestaurante, "restaurante prueba", "direccion prueba", "ciudad prueba", "telefono prueba");
        
        IRestauranteAccess service = Factory.getInstance().getRestauranteService();
        
        Restaurante restauranteEncontrado = service.getRestaurante(idRestaurante);
        
        if(restauranteEncontrado!=null){
            String mensaje = service.deleteRestaurante(idRestaurante);
            assertEquals(mensaje,"Restaurante eliminado correctamente");
        }
        
        assertEquals(service.addRestaurante(restauranteAgregar),"Restaurante añadido correctamente");
        
        
        
        Restaurante restauranteEsperado = service.getRestaurante(idRestaurante);
        assertEquals(restauranteEsperado.getId(),restauranteAgregar.getId());
        assertEquals(restauranteEsperado.getNombre(),restauranteAgregar.getNombre());
        assertEquals(restauranteEsperado.getCiudad(),restauranteAgregar.getCiudad());
        assertEquals(restauranteEsperado.getTelefono(),restauranteAgregar.getTelefono());
        assertEquals(restauranteEsperado.getDireccion(),restauranteAgregar.getDireccion());
        
        String mensaje = service.deleteRestaurante(idRestaurante);
        assertEquals(mensaje,"Restaurante eliminado correctamente");
        
        if(restauranteEncontrado!=null){
            assertEquals(service.addRestaurante(restauranteEncontrado),"Restaurante añadido correctamente");
        }
        
    }
}
