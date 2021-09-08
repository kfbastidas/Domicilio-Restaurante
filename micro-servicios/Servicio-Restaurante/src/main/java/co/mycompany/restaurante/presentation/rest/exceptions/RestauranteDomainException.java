package co.mycompany.restaurante.presentation.rest.exceptions;

import java.util.List;

/**
 * Lista de errores del dominio para un restaurante
 * 
 * * @author Kevith Felipe Bastidas
 *
 */
public class RestauranteDomainException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Listado de errores
	 */
	public final List<RestauranteError> errors;

	public RestauranteDomainException(List<RestauranteError> errors) {
		this.errors = errors;
	}
}
