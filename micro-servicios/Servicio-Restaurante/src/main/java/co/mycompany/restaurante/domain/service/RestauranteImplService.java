package co.mycompany.restaurante.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.mycompany.restaurante.access.dao.IRestauranteDao;
import co.mycompany.restaurante.domain.entity.Restaurante;
import co.mycompany.restaurante.presentation.rest.exceptions.ResourceNotFoundException;
import co.mycompany.restaurante.presentation.rest.exceptions.RestauranteDomainException;
import co.mycompany.restaurante.presentation.rest.exceptions.RestauranteError;

/**
 * Implementación de la Interfaz IRestauranteService
 * @author Kevith Felipe Bastidas
 *
 */
@Service
public class RestauranteImplService implements IRestauranteService{
	/**
	 * Inyección de Restaurante Dao
	 */
	@Autowired
	private IRestauranteDao restauranteDao;

	/**
	 * Servicio para buscar todos los restaurantes 
	 *  
	 * @return Listado de restaurantes
	 */
	@Override
	@Transactional(readOnly = true) // Para que esté sincronizada con la bd
	public List<Restaurante> findAll() {
		return (List<Restaurante>) restauranteDao.findAll();
	}
	
	
	/**
	 * Servicio para buscar restaurante por ID
	 * 
	 * @param id del restaurante
	 * @return Restaurante
	 */
	@Override
	public Restaurante findById(Long id) throws RestauranteDomainException {
		Restaurante restaurante = restauranteDao.findById(id).orElse(null);
		List<RestauranteError> errors = validateDomain(restaurante);
		if(!errors.isEmpty()) {
			throw new RestauranteDomainException(errors);
		}
		return restaurante;
	}
	
	/**
	* Crea un nuevo restaurante
	* 
	* @param restaurant producto a crear en la bd
	* @return restaurant creado
	*/
	@Override
	public Restaurante create(Restaurante restaurante) throws RestauranteDomainException {
		// TODO Auto-generated method stub
		
		List<RestauranteError> errors = validateDomain(restaurante);

		if (!errors.isEmpty()) {
			throw new RestauranteDomainException(errors);
		}
		
		return restauranteDao.save(restaurante);
	}
	
	/**
	* Servicio para actualizar la informacion del restaurante
	* 
	* @param Objeto Restaurante
	* @param Id Restaurante
	* @return Restaurante modificado
	*/
	@Override
	@Transactional
	public Restaurante update(Long id, Restaurante restaurante) throws RestauranteDomainException, ResourceNotFoundException {
		
		Restaurante restauranteSaved = this.findById(id);
		if (restauranteSaved == null) {
			throw new ResourceNotFoundException();
		}
		
		List<RestauranteError> errors = validateDomain(restauranteSaved);

		if (!errors.isEmpty()) {
			throw new RestauranteDomainException(errors);
		}

		restauranteSaved.setNombre(restaurante.getNombre());
		restauranteSaved.setDireccion(restaurante.getDireccion());
		restauranteSaved.setCiudad(restaurante.getCiudad());
		restauranteSaved.setTelefono(restaurante.getTelefono());
		
		return restauranteDao.save(restauranteSaved);
		
	}
	
	/**
	 * Valida las reglas del dominio para las operaciones requeridas.
	 * @param restaurante que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<RestauranteError> validateDomain(Restaurante restaurante) throws RestauranteDomainException{
		List<RestauranteError> errors = new ArrayList<>();
		
		if (restaurante == null) {
			errors.add(new RestauranteError(EnumErrorCodes.NOT_FOUND, "Id", "No existe ID"));
		}else {
			if (restaurante.getId()<=0) {
				errors.add(new RestauranteError(EnumErrorCodes.INVALID_NUMBER, "Id",
						"El Id debe ser mayor a 0"));
			}
		}
		return errors;
	}

	/**
	* Eliminar restaurante por su id
	* 
	* @param id identificador del restaurante a eliminar
	*/
	@Override
	public void deleteById(Long id) throws RestauranteDomainException {
		// TODO Auto-generated method stub
		Restaurante restaurante = this.findById(id);
		List<RestauranteError> errors = validateDomain(restaurante);
		if (!errors.isEmpty()) {
			throw new RestauranteDomainException(errors);
		}
		restauranteDao.deleteById(id);
	}
	
	
}
