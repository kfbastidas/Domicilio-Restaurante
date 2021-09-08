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
import co.mycompany.Componente.domain.service.IComponenteService;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteDomainException;
import co.mycompany.Componente.presentation.rest.exceptions.ResourceNotFoundException;
/**
 * Servicios web de platos
 * 
 * @author Kevith Felipe Bastidas
 *
 */
@RestController
@RequestMapping("componente")
public class ComponenteController {
	@Autowired
	private IComponenteService componenteService;

	/**
	 * Listar todos
	 * 
	 * @return listado de platos en json
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Componente> findAll() {
		return componenteService.findAll();
	}

	/**
	 * Listar un plato por id
	 * 
	 * @param id identificador
	 * @return Plato en formato json
	 * @throws ComponenteDomainException 
	 * @throws Exception
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Componente findById(@PathVariable Long id) throws ComponenteDomainException {
		return componenteService.findById(id);
	}
	
	
	/**
	 * Crear un plato
	 * 
	 * @param plat plato a crear
	 * @return Plato creado
	 */

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Componente create(@RequestBody Componente componente) throws ComponenteDomainException {
		return componenteService.create(componente);
	}

	/**
	 * Editar
	 * 
	 * @param plat Plato a editar
	 * @param id identificador del plato
	 * @return plato editado
	 * @throws ResourceNotFoundException recurso no encontrado
	 * @throws Exception Id no encontrado
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Componente update(@PathVariable Long id, @RequestBody Componente componente) throws ComponenteDomainException, ResourceNotFoundException {
		return componenteService.update(id, componente);
	}

	/**
	 * Eliminar
	 * 
	 * @param id id del plato
	 * @throws ResourceNotFoundException id no encontrado
	 */
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody	
	public Componente delete(@PathVariable Long id) throws ComponenteDomainException {
		return componenteService.deleteById(id);
	}
}