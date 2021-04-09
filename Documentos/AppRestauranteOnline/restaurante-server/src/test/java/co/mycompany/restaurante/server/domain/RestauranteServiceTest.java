package co.mycompany.restaurante.server.domain;
import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Plato;
import co.mycompany.restaurante.server.domain.services.RestauranteService;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.server.access.Factory;
import co.mycompany.restaurante.server.access.IRestauranteRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 *
 * @author Kevith Felipe Bastidas
 */
public class RestauranteServiceTest {     
//    @Test
//    public void testupdatePlato() {  
//        System.out.println("update plato para el restaurante 1");      
//        IRestauranteRepository repo = Factory.getInstance().getRepository();
//        RestauranteService instance = new RestauranteService(repo);
//        
//        Plato plato = new Plato(1, "prueba unitaria", 10000, 0);
//        
//        String resp = instance.updatePlato(1, plato);
//        System.out.println(resp);
//        assertEquals("Plato update correctamente",resp);
//    }
//    @Test
//    public void testgetMenuDia() {  
//        System.out.println("darMenuDia del id restaurante: 1 y dia Lunes");      
//        IRestauranteRepository repo = Factory.getInstance().getRepository();
//        RestauranteService instance = new RestauranteService(repo);
//        
//        ArrayList<Componente> menu = instance.getMenuComponentes(1, DiaSemana.LUNES); 
//        
//        Gson gson = new Gson();
//        String strObject = gson.toJson(menu);
//        System.out.println(strObject);
//        assertEquals(15, menu.size());
//    }   
//    @Test
//    public void testgetRestaurantes() {  
//        System.out.println("darRestaurantes");      
//        IRestauranteRepository repo = Factory.getInstance().getRepository();
//        RestauranteService instance = new RestauranteService(repo);
//        
//        ArrayList<Restaurante> restaurante = instance.getRestaurantes(); 
//        assertEquals(4, restaurante.size());
//    }      
//    @Test
//    public void testgetComponentes() {  
//        System.out.println("darComponentes");      
//        IRestauranteRepository repo = Factory.getInstance().getRepository();
//        RestauranteService instance = new RestauranteService(repo);
//        
//        ArrayList<Componente> componentes = instance.getComponentes(); 
//         Gson gson = new Gson();
//        String strObject = gson.toJson(componentes);
//        System.out.println(strObject);
//        assertEquals(19, componentes.size());
//    }      
//    @Test
//    public void testgetAdministrador() {  
//        System.out.println("darAdministradro");      
//        IRestauranteRepository repo = Factory.getInstance().getRepository();
//        RestauranteService instance = new RestauranteService(repo);
//        
//        String clave = instance.getAdministrador("admin");
//        System.out.println("clave: "+clave);
//        assertEquals(clave, "123456");
//    }  
    /*
    //Pasa la prueba solo cuando esta la base de datos vacia
//    @Test
//    public void testaddComponente() {  
//        System.out.println("addComponente");      
//        IRestauranteRepository repo = Factory.getInstance().getRepository();
//        RestauranteService instance = new RestauranteService(repo);
//        //insert into componente values(4,'masamorra','ENTRADA');
//        Componente c = new Componente(4, "masamorra", TipoComponente.ENTRADA);
//        String result = instance.addComponente(c);
//        System.out.println(result);
//        assertEquals(result, "Componente añadido correctamente");
//    }  
    
//    @Test
//    public void testaddMenuSemanal() {  
//        System.out.println("addMenuSemanal al restaurante id = 1");      
//        IRestauranteRepository repo = Factory.getInstance().getRepository();
//        RestauranteService instance = new RestauranteService(repo);
//        //insert into restcomponente values(1,1,'lentejas','LUNES');
//        ArrayList<DiaSemana> dias = new ArrayList<>();
//        dias.add(DiaSemana.MARTES);
//        dias.add(DiaSemana.MIERCOLES);
//        dias.add(DiaSemana.JUEVES);
//        dias.add(DiaSemana.VIERNES);
//        dias.add(DiaSemana.SABADO);
//        dias.add(DiaSemana.DOMINGO);
//        Componente c = new Componente(1, "lentejas", TipoComponente.ENTRADA);
//        c.setDias(dias);
//        ArrayList<Componente> menuSemanal = new ArrayList<>();
//        menuSemanal.add(c);
//        String result = instance.addMenuSemanal(1, menuSemanal);
//        System.out.println(result);
//        assertEquals(result, "Se añadido Correctamente el menu semanal");
//    }  
    */
}
