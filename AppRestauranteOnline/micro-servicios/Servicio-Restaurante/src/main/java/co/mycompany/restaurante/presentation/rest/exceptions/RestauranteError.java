package co.mycompany.restaurante.presentation.rest.exceptions;

import co.mycompany.restaurante.domain.service.EnumErrorCodes;

/**
 * Error de un restaurante
 * 
 * * @author Kevith Felipe Bastidas
 *
 */
public class RestauranteError {
	/**
	 * Codigo del error
	 */
	public final EnumErrorCodes code;
	/**
	 * Campo del error
	 */
	public final String field;
	/**
	 * Descripción del error
	 */
	public final String description;

	public RestauranteError(EnumErrorCodes code, String field, String description) {
		this.code = code;
		this.field = field;
		this.description = description;
	}
	
}
