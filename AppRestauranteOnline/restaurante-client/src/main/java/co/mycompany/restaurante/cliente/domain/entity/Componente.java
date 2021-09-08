package co.mycompany.restaurante.cliente.domain.entity;

import java.util.Comparator;

/**
 *
 * @author kevith felipe bastidas
 */
public class Componente implements Comparable<Componente>{
    private Integer id;
    private String nombre;
    private TipoComponente tipo;
    private DiaSemana dia;
    public Componente() {}

    public Componente(int id, String nombre, TipoComponente tipo,DiaSemana dia) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.dia = dia;
    }

    public Componente(int id, String nombre, TipoComponente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoComponente getTipo() {
        return tipo;
    }

    public void setTipo(TipoComponente tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Componente{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", dia=" + dia + '}';
    }
    
    @Override
    public int compareTo(Componente o) {
        return this.id.compareTo(o.getId());
    }
    
    public static Comparator<Componente> ID = Componente::compareTo;
    
    public static Comparator<Componente> NAME = new Comparator<Componente>() {
        @Override
        public int compare(Componente o1, Componente o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    };
    
    public static Comparator<Componente> TYPE = new Comparator<Componente>() {
        @Override
        public int compare(Componente o1, Componente o2) {
            return o1.getTipo().name().compareTo(o2.getTipo().name());
        }
    };
}
