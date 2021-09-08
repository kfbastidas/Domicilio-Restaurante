package co.mycompany.restaurante.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import co.mycompany.restaurante.domain.entity.Restaurante;
import co.mycompany.restaurante.domain.service.IRestauranteService;
import co.mycompany.restaurante.presentation.rest.exceptions.ResourceNotFoundException;
import co.mycompany.restaurante.presentation.rest.exceptions.RestauranteDomainException;

/**
* Servicio web de restaurantes
* @author Kevith Felipe Bastidas
*/
@RestController
@RequestMapping("restaurante")
public class RestauranteController {
	@Autowired
	private IRestauranteService restauranteService;

	/**
	 * Listar todos los restaurantes
	 * 
	 * @return listado de restaurantes en json
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Restaurante> findAll() {
		return (List<Restaurante>) restauranteService.findAll();
	}
	
	/**
	 * Obtener restaurante por ID
	 * @param id del restaurante
	 * @return Restaurante encontrado por el Id 
	 * @throws RestauranteDomainException
	 * 
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Restaurante findById(@PathVariable Long id) throws RestauranteDomainException, ResourceNotFoundException{
		return restauranteService.findById(id);
	}
	
	/**
	 * Actualizar estado de restaurante 
	 * @param rest:Restaurante
	 * @param restnit: Id del restaurante
	 * @return Restaurante actualizado
	 * @throws RestauranteDomainException
	 * 
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Restaurante update(@PathVariable Long id,@RequestBody Restaurante restaurante) throws RestauranteDomainException, ResourceNotFoundException {
		return restauranteService.update(id,restaurante);
	}
	
	/**
	 * Crear un restaurante
	 * 
	 * @param restaurante producto
	 * @return restaurante creado
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Restaurante create(@RequestBody Restaurante restaurant) throws RestauranteDomainException {
		return restauranteService.create(restaurant);
	}
	
	/**
	 * Eliminar
	 * 
	 * @param id id del restaurante
	 * @throws ResourceNotFoundException id no encontrado
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws RestauranteDomainException{
		restauranteService.deleteById(id);
	}
}