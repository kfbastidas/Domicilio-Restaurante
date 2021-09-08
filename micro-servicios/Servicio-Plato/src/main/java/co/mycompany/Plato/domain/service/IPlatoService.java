package co.mycompany.Plato.domain.service;
import co.mycompany.Plato.domain.entity.Plato;
import co.mycompany.Plato.presentation.rest.exceptions.PlatoDomainException;

/**
 * Interfaz de operaciones de la bd de platos
 * 
 * @author Kevith Felipe Bastidas
 *
 */

public interface IPlatoService {
	
	public Plato findPlato(Long idRest,String dia) throws PlatoDomainException;

	public Plato create(Plato plato) throws PlatoDomainException;

	public Plato update(Long idRest,String dia, Plato plato) throws PlatoDomainException;

	public void deletePlato(Long idRest,String dia) throws PlatoDomainException;
}
