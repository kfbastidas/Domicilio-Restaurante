package co.mycompany.Componente.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.mycompany.Componente.domain.entity.Componente;
import co.mycompany.Componente.domain.entity.RestComponente;
import co.mycompany.Componente.domain.service.IRestComponenteService;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteDomainException;
import co.mycompany.Componente.presentation.rest.exceptions.ResourceNotFoundException;
/**
 * Servicios web de platos
 * 
 * @author Kevith Felipe Bastidas
 *
 */
@RestController
@RequestMapping("restcomponente")
public class RestComponenteController {
	@Autowired
	private IRestComponenteService restComponenteService;


	/**
	 * Listar un plato por id
	 * 
	 * @param id identificador
	 * @return Plato en formato json
	 * @throws ComponenteDomainException 
	 * @throws Exception
	 */
	@RequestMapping(value = "{id}/{dia}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Componente> findComponentes(@PathVariable Long id,@PathVariable String dia) throws ComponenteDomainException {
		return restComponenteService.findComponentes(id, dia);
	}
	
	
	/**
	 * Crear un plato
	 * 
	 * @param plat plato a crear
	 * @return Plato creado
	 */

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestComponente createComponenteDia(@RequestBody RestComponente restComp) throws ComponenteDomainException {
		return restComponenteService.createComponenteDia(restComp);
	}
	

	/**
	 * Eliminar
	 * 
	 * @param id id del plato
	 * @throws ResourceNotFoundException id no encontrado
	 */
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{idRest}/{idComp}/{dia}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody	
	public void deleteComponente(@PathVariable Long idRest,@PathVariable Long idComp,@PathVariable String dia) throws ComponenteDomainException {
		restComponenteService.deleteComponente(idRest, idComp, dia);
	}
}