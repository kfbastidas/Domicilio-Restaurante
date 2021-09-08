package co.mycompany.Componente.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.mycompany.Componente.domain.entity.Componente;
import co.mycompany.Componente.domain.entity.RestComponente;
import co.mycompany.Componente.domain.entity.Restaurante;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteDomainException;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteError;

/**
 * Implementa los servicios de los componentes
 * @author kevith Felipe Bastidas
 *
 */
@Service
public class ComponenteImplService implements IRestComponenteService{
	/**
	 * Inyeccion de componente dao
	 */
	@Autowired
	private IComponenteClientRest componenteClientRest;
	
	@Autowired
	private IRestauranteClientRest restuaranteClienteRest;
	
	@Autowired
	private IRestComponenteClientRest restcomponenteRest;
	
	/**
	 * Encuentra un componente con un id especifico
	 * @param id
	 * @return el componente si existe si no null
	 * @throws ComponenteDomainException 
	 */
	@Override
	public List<Componente> findComponentes(Long idRest, String dia) throws ComponenteDomainException {
		List<Long> idComponentes = restcomponenteRest.findComponentes(idRest, dia);
		List<Componente> componentes = new ArrayList<>();
		for(long idComp: idComponentes) {
			Componente c = componenteClientRest.detail(idComp);
			c.setDia(dia);
			componentes.add(c);
		}
		
		List<ComponenteError> errors = validateFindComponente(idComponentes,componentes);
		if (!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		
		return componentes;
	}
	
	/**
	 * 
	 * Agrega un nuevo componente no existe a la base de datos.
	 * @param componente
	 * @return el componente agregado
	 */
	@Override
	public RestComponente createComponenteDia(RestComponente restComp) throws ComponenteDomainException{
		List<ComponenteError> errors = validateCrearComponente(restComp);
		if(!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		return restcomponenteRest.createComponenteDia(restComp);
	}
	
	
	/**
	 * Elimina el componente de la base de datos
	 * @param id
	 */
	@Override
	public void deleteComponente(Long idRest,Long idComp, String dia) throws ComponenteDomainException{
		RestComponente restComp = new RestComponente(idRest,idComp,dia);
		List<ComponenteError> errors = validateCrearComponente(restComp);
		if(!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		restcomponenteRest.delete(idRest, idComp, dia);
	}
	
	/**
	 * Valida que el componente con ese id exista.
	 * @param componente que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateFindComponente(List<Long> idComponentes,List<Componente> componentes){
		List<ComponenteError> errors = new ArrayList<>();	
		if (idComponentes.isEmpty()) {
			errors.add(new ComponenteError(EnumErrorCodes.NOT_FOUND, "List<Long>", "No encontro los id de los componentes"));
		}
		if (componentes.isEmpty()) {
			errors.add(new ComponenteError(EnumErrorCodes.NOT_FOUND, "List<Componente>", "No encontro componentes con ese id"));
		}
		return errors;
	}

	/**
	 * Valida el componente ha crear.
	 * @param componente que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateCrearComponente(RestComponente restComp){
		List<ComponenteError> errors = new ArrayList<>();
		if(restComp.getIdRest()<=0) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "IdRest","El IdRest debe ser mayor a 0"));
		}else {
			Restaurante r = restuaranteClienteRest.detail(restComp.getIdRest());
			if (r==null) {
				errors.add(new ComponenteError(EnumErrorCodes.NOT_FOUND, "IdRest","El Restaurante con el IdRest ingresado no existe."));
			}
		}
		if(restComp.getIdComp()<=0) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "IdComp","El IdComp debe ser mayor a 0"));
		}else {
			Componente c = componenteClientRest.detail(restComp.getIdComp());
			if (c==null) {
				errors.add(new ComponenteError(EnumErrorCodes.NOT_FOUND, "IdComp","El Componente con el IdComp ingresado no existe."));
			}
		}
		if(!validateDomainDia(restComp.getDia())) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_DOMAIN, "Dia","El campo dia debe ser de tipo: "
					+ "LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO O DOMINGO"));
		}
		return errors;
	}
	
	/**
	 * Valida que el día sea valido
	 * @param tipo
	 * @return
	 */
	private boolean validateDomainDia(String dia) {
		switch (dia) {
		case "LUNES":
			return true;
		case "MARTES":
			return true;
		case "MIERCOLES":
			return true;
		case "JUEVES":
			return true;
		case "VIERNES":
			return true;
		case "SABADO":
			return true;
		case "DOMINGO":
			return true;
		default:
			break;
		}
		return false;
	}
	
}
