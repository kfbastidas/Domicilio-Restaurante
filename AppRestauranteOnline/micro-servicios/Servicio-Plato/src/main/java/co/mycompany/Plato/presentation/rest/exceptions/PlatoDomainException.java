package co.mycompany.Plato.presentation.rest.exceptions;

import java.util.List;

/**
 * Lista de errores del dominio para un plato
 * 
 * * @author Kevith Felipe Bastidas
 *
 */
public class PlatoDomainException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Listado de errores
	 */
	public final List<PlatoError> errors;

	public PlatoDomainException(List<PlatoError> errors) {
		this.errors = errors;
	}
}
