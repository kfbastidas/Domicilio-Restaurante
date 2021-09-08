package co.mycompany.restaurante.cliente.infra;

import java.io.Serializable;


/**
 * 
 * @author Kevith Felipe Bastidas
 *
 */
public class RestComponente implements Serializable {

    private int idRest;
    private int idComp;
    private String dia;

    /**
     * Constructor
     * @param idRest
     * @param idComp
     * @param dia 
     */
    public RestComponente(int idRest, int idComp, String dia) {
        this.idRest = idRest;
        this.idComp = idComp;
        this.dia = dia;
    }

    public RestComponente() {

    }

    

    public int getIdRest() {
        return idRest;
    }

    public void setIdRest(int idRest) {
        this.idRest = idRest;
    }

    public int getIdComp() {
        return idComp;
    }

    /**
     * Gets and Sets
     * @param idComp
     */
    public void setIdComp(int idComp) {    
        this.idComp = idComp;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "RestComponente [idRest=" + idRest + ", idComp=" + idComp + ", dia=" + dia + "]";
    }

}
