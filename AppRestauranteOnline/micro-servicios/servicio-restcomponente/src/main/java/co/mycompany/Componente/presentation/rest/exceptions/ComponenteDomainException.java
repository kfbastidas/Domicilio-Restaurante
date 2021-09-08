package co.mycompany.Componente.presentation.rest.exceptions;

import java.util.List;

/**
 * Lista de errores del dominio para un componente de un plato
 * 
 * * @author Kevith Felipe Bastidas
 *
 */
public class ComponenteDomainException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Listado de errores
	 */
	public final List<ComponenteError> errors;

	public ComponenteDomainException(List<ComponenteError> errors) {
		this.errors = errors;
	}
}
