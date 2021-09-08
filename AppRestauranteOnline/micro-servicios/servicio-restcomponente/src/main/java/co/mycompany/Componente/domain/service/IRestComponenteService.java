package co.mycompany.Componente.domain.service;

import java.util.List;
import co.mycompany.Componente.domain.entity.RestComponente;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteDomainException;

/**
 * Interfaz de operaciones de la bd de componente
 * @author kevith Felipe Bastidas
 *
 */
public interface IRestComponenteService {
	
	/**
	 * Encuentra una lista de componentes con el id del restaurante y un dia determinado.
	 * @param id
	 * @param dia
	 * @return
	 * @throws ComponenteDomainException
	 */
	public List<Long> findMenuComponentes(Long idRest, String dia) throws ComponenteDomainException;
	
	/**
	 * 
	 * Agrega el id del restaurante junto con el id del componente en un dia determinado
	 * @param componente
	 * @return el componente agregado
	 */
	public RestComponente createComponenteSemanal(RestComponente restComp) throws ComponenteDomainException;
		
	/**
	 * Elimina el componente de la base de datos
	 * @param id
	 */
	public void deleteComponenteSemanal(Long idRest,Long idComp, String dia) throws ComponenteDomainException;
}
