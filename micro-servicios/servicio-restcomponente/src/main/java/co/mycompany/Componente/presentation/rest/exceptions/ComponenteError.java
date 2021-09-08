package co.mycompany.Componente.presentation.rest.exceptions;

import co.mycompany.Componente.domain.service.EnumErrorCodes;

/**
 * Error de un Componente
 * 
 * * @author Kevith Felipe Bastidas
 *
 */
public class ComponenteError {
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

	public ComponenteError(EnumErrorCodes code, String field, String description) {
		this.code = code;
		this.field = field;
		this.description = description;
	}
}
