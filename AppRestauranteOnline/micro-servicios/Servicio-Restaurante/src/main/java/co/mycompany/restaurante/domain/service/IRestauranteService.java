package co.mycompany.restaurante.domain.service;

import java.util.List;

import co.mycompany.restaurante.domain.entity.Restaurante;
import co.mycompany.restaurante.presentation.rest.exceptions.ResourceNotFoundException;
import co.mycompany.restaurante.presentation.rest.exceptions.RestauranteDomainException;

/**
 * Interfaz de operaciones de la BD de Restaurantes 
 * @author Kevith Felipe Bastidas
 */

public interface IRestauranteService {
	public List<Restaurante> findAll();
	
	public Restaurante findById(Long id) throws RestauranteDomainException;
	
	public Restaurante create(Restaurante restaurante) throws RestauranteDomainException;
	
	public Restaurante update(Long id, Restaurante restaurante) throws RestauranteDomainException, ResourceNotFoundException;

	public void deleteById(Long id) throws RestauranteDomainException;
}
