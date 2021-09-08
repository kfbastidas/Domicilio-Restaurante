package co.mycompany.Componente.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.mycompany.Componente.access.dao.IComponenteDao;
import co.mycompany.Componente.domain.entity.Componente;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteDomainException;
import co.mycompany.Componente.presentation.rest.exceptions.ComponenteError;
import co.mycompany.Componente.presentation.rest.exceptions.ResourceNotFoundException;

/**
 * Implementa los servicios de los componentes
 * @author kevith Felipe Bastidas
 *
 */
@Service
public class ComponenteImplService implements IComponenteService{
	/**
	 * Inyeccion de componente dao
	 */
	@Autowired
	private IComponenteDao componenteDao;
	
	/**
	 * Encuentra todos los componentes de la base de datos
	 * @return todos los componentes encontrados
	 */
	@Override
	@Transactional(readOnly = true) // Para que esté sincronizada con la bd
	public List<Componente> findAll(){
		return (List<Componente>) componenteDao.findAll();
	}
	
	/**
	 * Encuentra un componente con un id especifico
	 * @param id
	 * @return el componente si existe si no null
	 * @throws ComponenteDomainException 
	 */
	@Override
	public Componente findById(Long id) throws ComponenteDomainException {
		Componente c = componenteDao.findByIdComponente(id);
		List<ComponenteError> errors = validateId(c);
		if (!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		return componenteDao.findByIdComponente(id);
	}
	
	/**
	 * 
	 * Agrega un nuevo componente no existe a la base de datos.
	 * @param componente
	 * @return el componente agregado
	 */
	@Override
	public Componente create(Componente componente) throws ComponenteDomainException{
		List<ComponenteError> errors = validateCrearComponente(componente);
		if(!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		return componenteDao.save(componente);
	}
	
	/**
	 * Actualiza el componente de la base de datos.
	 * @param id
	 * @param componente
	 * @return el componente actualizado.
	 */
	@Override
	public Componente update(Long id, Componente componente) throws ComponenteDomainException,ResourceNotFoundException{
		Componente c = componenteDao.findByIdComponente(id);
		
		if(c==null) {
			throw new ResourceNotFoundException();
		}
		
		List<ComponenteError> errors = validateUpdateComponente(componente);
		if(!errors.isEmpty()) {
			throw new ComponenteDomainException(errors);
		}
		
		c.setNombre(componente.getNombre());
		c.setTipo(componente.getTipo());
		
		return componenteDao.save(c);
	}
	
	/**
	 * Elimina el componente de la base de datos
	 * @param id
	 */
	@Override
	public Componente deleteById(Long id) throws ComponenteDomainException{
		Componente c = findById(id);
		componenteDao.deleteById(id);
		return c;
	}
	
	/**
	 * Valida que el componente con ese id exista.
	 * @param componente que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateId(Componente componente){
		List<ComponenteError> errors = new ArrayList<>();		
		if (componente == null) {
			errors.add(new ComponenteError(EnumErrorCodes.NOT_FOUND, "Id", "No existe ID"));
		}
		return errors;
	}

	/**
	 * Valida el componente ha crear.
	 * @param componente que se desea validar 
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateCrearComponente(Componente componente){
		List<ComponenteError> errors = new ArrayList<>();
		if(componente.getId()==null) {
			errors.add(new ComponenteError(EnumErrorCodes.NULL, "Id","El campo del id es nulo"));
		}else if(componente.getId()<=0) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_NUMBER, "Id","El Id debe ser mayor a 0"));
		}
		if(componente.getNombre()==null) {
			errors.add(new ComponenteError(EnumErrorCodes.NULL, "Nombre","El campo del nombre es nulo"));
		}else if(componente.getNombre().equals("")) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_STRING, "Nombre","El campo del nombre esta vacio"));
		}
		if(componente.getTipo()==null) {
			errors.add(new ComponenteError(EnumErrorCodes.NULL, "Tipo","El campo del tipo es nulo"));
		}else if(componente.getTipo().equals("")) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_STRING, "Tipo","El campo del tipo esta vacio"));
		}else if(!validateDomainTipo(componente.getTipo())) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_DOMAIN, "Tipo","El campo tipo debe ser de tipo: "
					+ "BEBIDA, PRINCIPIO, ENTRADA O PROTEINA"));
		}
		Componente c = componenteDao.findByIdComponente(componente.getId());
		if(c!=null) {
			errors.add(new ComponenteError(EnumErrorCodes.FOUND, "Id", "El componente con ese Id ya existe"));
		}
		Componente c2 = componenteDao.findByName(componente.getNombre());
		if(c2!=null) {
			errors.add(new ComponenteError(EnumErrorCodes.FOUND, "Nombre", "El componente con ese Nombre ya existe"));
		}
		return errors;
	}
	
	/**
	 * Valida que el componente pueda ser actualizado
	 * @param componente que se desea validar 
	 * @Param id
	 * @return lista de errores al valdidar
	 */
	private List<ComponenteError> validateUpdateComponente(Componente componente){
		List<ComponenteError> errors = new ArrayList<>();
		
		if(componente.getNombre()==null) {
			errors.add(new ComponenteError(EnumErrorCodes.NULL, "Nombre","El campo del nombre es nulo"));
		}else if(componente.getNombre().equals("")) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_STRING, "Nombre","El campo del nombre esta vacio"));
		}
		if(componente.getTipo()==null) {
			errors.add(new ComponenteError(EnumErrorCodes.NULL, "Tipo","El campo del tipo es nulo"));
		}else if(componente.getTipo().equals("")) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_STRING, "Tipo","El campo del tipo esta vacio"));
		}else if(!validateDomainTipo(componente.getTipo())) {
			errors.add(new ComponenteError(EnumErrorCodes.INVALID_DOMAIN, "Tipo","El campo tipo debe ser de tipo: "
					+ "BEBIDA, PRINCIPIO, ENTRADA O PROTEINA"));
		}
		return errors;
	}
	
	/**
	 * Valida que el tipo sea valido
	 * @param tipo
	 * @return
	 */
	private boolean validateDomainTipo(String tipo) {
		switch (tipo) {
		case "BEBIDA":
			return true;
		case "PRINCIPIO":
			return true;
		case "ENTRADA":
			return true;
		case "PROTEINA":
			return true;
		default:
			break;
		}
		return false;
	}
	
}
