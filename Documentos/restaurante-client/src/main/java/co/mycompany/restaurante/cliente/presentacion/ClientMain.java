/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.cliente.presentacion;

import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import co.mycompany.restaurante.commons.domain.Restaurante;

/**
 *
 * @author kevit
 */
public class ClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RestauranteService service = new RestauranteService();
        GUIPagPrincipalRestaurantes.getInstance(service).setVisible(true);
        service.addObserver(GUIPagPrincipalRestaurantes.getInstance(service));
        service.addObserver(GUIPagMenuRestaurante.getInstance(new Restaurante(), service,null));
    }
}
