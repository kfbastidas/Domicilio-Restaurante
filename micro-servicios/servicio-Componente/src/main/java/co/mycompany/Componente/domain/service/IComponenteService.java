package co.mycompany.Componente.domain.service;

import java.util.List;
import co.mycompany.Componente.domain.entity.Componente;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteDomainException;
import co.mycompany.Componente.presentation.rest.exceptions.ResourceNotFoundException;

/**
 * Interfaz de operaciones de la bd de componente
 * @author kevith Felipe Bastidas
 *
 */
public interface IComponenteService {
	/**
	 * Encuentra todos los componentes de la base de datos
	 * @return todos los componentes encontrados
	 */
	public List<Componente> findAll();
	
	/**
	 * Encuentra un componente con un id especifico
	 * @param id
	 * @return el componente si existe si no null
	 */
	public Componente findById(Long id) throws ComponenteDomainException;
	
	/**
	 * 
	 * Agrega un nuevo componente no existe a la base de datos.
	 * @param componente
	 * @return el componente agregado
	 */
	public Componente create(Componente componente) throws ComponenteDomainException;
	
	/**
	 * Actualiza el componente de la base de datos.
	 * @param id
	 * @param componente
	 * @return el componente actualizado.
	 */
	public Componente update(Long id, Componente componente) throws ComponenteDomainException,ResourceNotFoundException;
	
	/**
	 * Elimina el componente de la base de datos
	 * @param id
	 */
	public Componente deleteById(Long id) throws ComponenteDomainException;
}
