package co.mycompany.restaurante.cliente.infra;

import co.mycompany.restaurante.commons.domain.Restaurante;
import java.util.ArrayList;

/**
 * Sujeto, o tambien el observadoF
 *
 * @author Kevith Felipe Bastidas
 */
public abstract class Subject {

    ArrayList<Observer> observers;

    public void Subject() {

    }

    /**
     * Agrega un observador
     *
     * @param obs
     */
    public void addObserver(Observer obs) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(obs);
    }

    /**
     * Notifica a todos los observadores que hubo un cambio en el modelo
     * @param idRest
     */
    public void notifyAllObserves(int idRest) {
        for (Observer each : observers) {
            each.update(idRest);
        }
    }

}
