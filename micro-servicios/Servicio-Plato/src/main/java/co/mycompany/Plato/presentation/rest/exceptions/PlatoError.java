package co.mycompany.Plato.presentation.rest.exceptions;

import co.mycompany.Plato.domain.service.EnumErrorCodes;

/**
 * Error de un plato
 * 
 * * @author Kevith Felipe Bastidas
 *
 */
public class PlatoError {
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

	public PlatoError(EnumErrorCodes code, String field, String description) {
		this.code = code;
		this.field = field;
		this.description = description;
	}
}
