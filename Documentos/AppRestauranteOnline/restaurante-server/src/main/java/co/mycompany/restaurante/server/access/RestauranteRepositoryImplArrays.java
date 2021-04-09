package co.mycompany.restaurante.server.access;

import co.mycompany.restaurante.commons.domain.Administrador;
import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Restaurante;
import co.mycompany.restaurante.commons.domain.Plato;
import co.mycompany.restaurante.commons.domain.TipoComponente;
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
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Componente> componentes;
    private ArrayList<Administrador> administradores;
    public RestauranteRepositoryImplArrays() {
        if (restaurantes == null){
            restaurantes = new ArrayList();
            
        }
        if (componentes==null) {
            componentes = new ArrayList<>();
        }
        if (administradores==null) {
            administradores = new ArrayList<>();
        }
        inicializarComponentes();
        inicializar();
        
    }
    private void inicializarComponentes(){
        //COMPONENTEES TIPO PRINCIPIO
        Componente componente1 = new Componente(1,"lentejas", TipoComponente.PRINCIPIO);
        Componente componente2 = new Componente(2,"frijoles", TipoComponente.PRINCIPIO);
        Componente componente3 = new Componente(3,"guiso de habichuela", TipoComponente.PRINCIPIO);
        Componente componente4 = new Componente(4,"garbanzos horneados", TipoComponente.PRINCIPIO);
        
        //COMPONENTES TIPO ENTRADA
        Componente componente5 = new Componente(1,"sopa de verduras", TipoComponente.ENTRADA);
        Componente componente6 = new Componente(2,"sancocho de gallina", TipoComponente.ENTRADA);
        Componente componente7 = new Componente(3,"sopa de costilla", TipoComponente.ENTRADA);
        Componente componente8 = new Componente(4,"masamorra", TipoComponente.ENTRADA);
        Componente componente9 = new Componente(5,"ensala de frutas", TipoComponente.ENTRADA);
        
        //COMPONENTES TIPO PROTEINA
        Componente componente10 = new Componente(1,"chuleta de pollo", TipoComponente.PROTEINA);
        Componente componente11 = new Componente(2,"chuleta de cerdo", TipoComponente.PROTEINA);
        Componente componente12 = new Componente(3,"carne frita", TipoComponente.PROTEINA);
        Componente componente13 = new Componente(4,"arroz con pollo con adicion de carne", TipoComponente.PROTEINA);
        
        //COCMPONENTES TIPO BEBIDA
        Componente componente14 = new Componente(1,"jugo de lulo", TipoComponente.BEBIDA);
        Componente componente15 = new Componente(2,"limonada", TipoComponente.BEBIDA);
        Componente componente16 = new Componente(3,"jugo de tomate", TipoComponente.BEBIDA);
        Componente componente17 = new Componente(3,"gaseosa de manzana", TipoComponente.BEBIDA);
        componentes.add(componente1);
        componentes.add(componente2);
        componentes.add(componente3);
        componentes.add(componente4);
        componentes.add(componente5);
        componentes.add(componente6);
        componentes.add(componente7);
        componentes.add(componente8);
        componentes.add(componente9);
        componentes.add(componente10);
        componentes.add(componente11);
        componentes.add(componente12);
        componentes.add(componente13);
        componentes.add(componente14);
        componentes.add(componente15);
        componentes.add(componente16);
        componentes.add(componente17);
    }
    private void addComponentesRestaurante(Restaurante restaurante){
        for (Componente componente : componentes) {
            Componente c = new Componente(componente.getId(), componente.getNombre(), componente.getTipo());
            addComponenteSemanal(restaurante.getId(),c , DiaSemana.LUNES);
        }
    }
    public void inicializar() {
        Restaurante restaurante = new Restaurante(1,"LA COSECHA","Cra 11 # 3-45","Popayan","800001");
        
        
        restaurante.setPlato(new Plato(1,"descripcion del plato",0,0));
        
        restaurantes.add(restaurante);
        addComponentesRestaurante(restaurante);
        restaurantes.add(new Restaurante(2,"RESTAURANTE 2","Cra 11 # 3-45","Popayan","800001"));
        restaurantes.add(new Restaurante(3,"RESTAURANTE 3","Cra 11 # 3-45","Popayan","800001"));
        restaurantes.add(new Restaurante(4,"RESTAURANTE 4","Cra 11 # 3-45","Popayan","800001"));
        
        Administrador a = new Administrador("admin","123456");
        a.setRestaurantes(restaurantes);
        administradores.add(a);
    }

    @Override
    public String addComponente(Componente componente) {
        for (Componente componente1 : componentes) {
            if (componente1.getId()==componente.getId() && componente1.getNombre().equals(componente.getNombre())) {
                return "Error, el componente con ese id y nombre ya existe";
            }
        }
        componentes.add(componente);
        return "Componente añadido correctamente";
    }

    @Override
    public ArrayList<Componente> getComponentes() {
        ArrayList<Componente> compons = new ArrayList<>();
        for (Componente componente : componentes) {
            Componente c = new Componente();
            c.setId(componente.getId());
            c.setNombre(componente.getNombre());
            c.setTipo(componente.getTipo());
            compons.add(c);
        }
        return compons;
    }

    @Override
    public ArrayList<Componente> getMenuComponentes(int idRestaurante, DiaSemana dia) {
        ArrayList<Componente> menucomponente = new ArrayList<>();
        for (Restaurante restaurante : restaurantes) {
            if (restaurante.getId()==idRestaurante) {
                for (Componente componente : restaurante.getMenuSemanal()) {
                    for (DiaSemana dia1 : componente.getDias()) {
                        if (dia1.name().equals(dia.name())) {
                            Componente c = new Componente();
                            c.setId(componente.getId());
                            c.setNombre(componente.getNombre());
                            c.setTipo(componente.getTipo());
                            menucomponente.add(c);
                        }
                    }
                }
            }
        }
        return menucomponente;
    }

    @Override
    public String addComponenteSemanal(int idRestaurante, Componente componente, DiaSemana dia) {
        for (Restaurante restaurante : restaurantes) {
            if (restaurante.getId() == idRestaurante) {
                for (Componente componente1 : restaurante.getMenuSemanal()) {
                    for (DiaSemana dia1 : componente1.getDias()) {
                        if (dia1.name().equals(dia.name()) && componente1.getNombre().equals(componente.getNombre())) {
                            return "Error el componente ya existe";
                        }
                    }
                }
                if (componente.getDias() == null) {
                    componente.setDias(new ArrayList<>());
                }
                componente.getDias().add(dia);
                restaurante.getMenuSemanal().add(componente);

            }
        }
        return "Menu Componente añadido correctamente";
    }

    @Override
    public String deleteComponenteSemanal(int idRestaurante, Componente componente, DiaSemana dia) {
        for (Restaurante restaurante : restaurantes) {
            if (restaurante.getId()==idRestaurante) {
                for (Componente componente1 : restaurante.getMenuSemanal()) {
                    for (DiaSemana dia1 : componente1.getDias()) {
                        if (dia1.equals(dia) && componente.getNombre().equals(componente1.getNombre())) {
                            restaurante.getMenuSemanal().remove(componente1);
                            return "Menu Componente se elimino correctamente";
                        }
                    }
                    
                }
                
            }
        }
        return "No se pudo eliminar el componente semanal";
    }

    @Override
    public String updatePlato(int idRestaurante, Plato plato) {
        for (Restaurante restaurante : restaurantes) {
            if (restaurante.getId()==idRestaurante) {
                
                if (plato == null) {
                    plato = new Plato(1, "", 0, 0);
                }else{
                    if (plato.getId()==1) {
                        restaurante.setPlato(plato);
                    }else{
                        plato.setId(1);
                        restaurante.setPlato(plato);
                    }
                    
                }
                
            }
        }
        return "Plato update correctamente";
    }

    @Override
    public ArrayList<Restaurante> getRestaurantes() {
        ArrayList<Restaurante> restaurantess = new ArrayList<>();
        for (Restaurante r : restaurantes) {
            Restaurante restaurante = new Restaurante();
            restaurante.setId(r.getId());
            restaurante.setNombre(r.getNombre());
            restaurante.setDirecccion(r.getDirecccion());
            restaurante.setCiudad(r.getCiudad());
            restaurante.setTelefono(r.getTelefono());
            restaurantess.add(restaurante);
        }
        return restaurantess;
    }

    @Override
    public Plato getPlato(int idRestaurantes) {
        for (Restaurante restaurante : restaurantes) {
            if (restaurante.getId()==idRestaurantes) {
                Plato p = new Plato();
                p.setId(restaurante.getPlato().getId());
                p.setDescripcion(restaurante.getPlato().getDescripcion());
                p.setCantidad(0);
                p.setPrecio(0);
                
                return p;
            }
        }
        return new Plato();
    }

    @Override
    public String getAdministrador(String usuario) {
        for (Administrador administrador : administradores) {
            if (administrador.getUsuario().equals(usuario)) {
                return administrador.getClave();
            }
        }
        
        return "null";
    }

    
    
}
