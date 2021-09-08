package co.mycompany.Componente.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.mycompany.Componente.access.dao.IRestComponenteDao;
import co.mycompany.Componente.domain.entity.RestComponente;
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
	private IRestComponenteDao restcomponenteDao;
	
	/**
	 * Encuentra un componente con un id especifico
	 * @param id
	 * @return el componente si existe si no null
	 * @throws ComponenteDomainException 
	 */
	@Override
	public List<Long> findMenuComponentes(Long idRest, String dia) throws ComponenteDomainException {
		List<ComponenteError> errors = validateIdDia(idRest,dia);
		if (!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		
		return restcomponenteDao.findIdComponentes(idRest, dia);
	}
	
	/**
	 * 
	 * Agrega un nuevo componente no existe a la base de datos.
	 * @param componente
	 * @return el componente agregado
	 */
	@Override
	public RestComponente createComponenteSemanal(RestComponente restComp) throws ComponenteDomainException{
		List<ComponenteError> errors = validateCrearComponenteDia(restComp);
		if(!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		restcomponenteDao.save(restComp);
		return restComp;
	}
	
	
	/**
	 * Elimina el componente de la base de datos
	 * @param id
	 */
	@Override
	public void deleteComponenteSemanal(Long idRest,Long idComp, String dia) throws ComponenteDomainException{
		RestComponente restcomp = restcomponenteDao.findComponente(idRest,idComp, dia);
		System.out.println(restcomp);
		List<ComponenteError> errors = validateDeleteComponenteSemanal(restcomp);
		if(!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		
		restcomponenteDao.deleteById(restcomp.getId());
	}
	
	/**
	 * Valida que el componente con ese id exista.
	 * @param componente que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateIdDia(Long idRest, String dia){
		List<ComponenteError> errors = new ArrayList<>();	
		
		if (idRest<0) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "IdRest","El Id debe ser mayor a 0"));
		}
		if(!validateDomainDia(dia)) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_DOMAIN, "Dia","El campo dia debe ser de tipo: "
					+ "LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO O DOMINGO"));
		}
		return errors;
	}

	/**
	 * Valida el componente ha crear.
	 * @param componente que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateCrearComponenteDia(RestComponente restComp){
		List<ComponenteError> errors = new ArrayList<>();
		if (restComp.getIdRest()<0) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "IdRest","El Id debe ser mayor a 0"));
		}
		if (restComp.getIdComp()<0) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "IdComp","El Id debe ser mayor a 0"));
		}
		if(!validateDomainDia(restComp.getDia())) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_DOMAIN, "Dia","El campo dia debe ser de tipo: "
					+ "LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO O DOMINGO"));
		}
		
		RestComponente restcomp = restcomponenteDao.findComponente(restComp.getIdRest(), restComp.getIdComp(), restComp.getDia());
		if (restcomp!=null) {
			errors.add(new ComponenteError(EnumErrorCodes.FOUND, "RestComponente","La entidad RestComponente ya existe"));
		}
		
		return errors;
	}

	/**
	 * Valida el componente ha crear.
	 * @param componente que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateDeleteComponenteSemanal(RestComponente restComp){
		List<ComponenteError> errors = new ArrayList<>();
		
		if (restComp==null) {
			errors.add(new ComponenteError(EnumErrorCodes.NULL, "RestComponente","La entidad RestComponente es nula"));
		}else {
			if (restComp.getIdRest()<0) {
				errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "IdRest","El Id debe ser mayor a 0"));
			}
			if (restComp.getIdComp()<0) {
				errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "IdComp","El Id debe ser mayor a 0"));
			}
			if(!validateDomainDia(restComp.getDia())) {
				errors.add(new ComponenteError(EnumErrorCodes.INVALID_DOMAIN, "Dia","El campo dia debe ser de tipo: "
						+ "LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO O DOMINGO"));
			}
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
