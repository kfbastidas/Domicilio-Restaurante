package co.mycompany.Plato.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.mycompany.Plato.access.dao.IPlatoDao;
import co.mycompany.Plato.domain.entity.Plato;
import co.mycompany.Plato.presentation.rest.exceptions.PlatoDomainException;
import co.mycompany.Plato.presentation.rest.exceptions.PlatoError;

/**
 * Implementación de la Interfaz IPlatoService
 * 
 * @author Kevith Felipe Bastidas
 *
 */

@Service
public class PlatoImplService implements IPlatoService {
	/**
	 * Inyección de plato Dao
	 */
	@Autowired
	private IPlatoDao platoDao;

	/**
	 * Busca un plato por su Id en caso de no existir el plato en un dia determinado
	 * se creara el plato
	 * 
	 * @param id identificador del plato
	 * @return objeto de tipo plato
	 */
	@Override // Para que esté sincronizada con la bd
	public Plato findPlato(Long idRest,String dia) throws PlatoDomainException {
		List<PlatoError> errors = validateFindPlato(idRest,dia);		
		Plato plato = platoDao.findPlato(idRest, dia);
		if(plato==null) {
			errors.add(new PlatoError(EnumErrorCodes.NULL, "Plato","El plato debe ser diferente de nulo"));
		}	
		if (!errors.isEmpty()) {
			throw new PlatoDomainException(errors);
		}
		return plato;
	}
	

	/**
	 * Valida que los parametros sean correctos 
	 * @param idRest
	 * @param dia
	 * @return
	 */
	private List<PlatoError> validateFindPlato(Long idRest, String dia) {
		List<PlatoError> errors = new ArrayList<>();
		if(idRest<0) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_NUMBER, "Id","El IdRest debe ser mayor a 0"));
		}
		if(!validateDomainDia(dia)) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_DOMAIN, "Dia","El campo dia debe ser de tipo: "
					+ "LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO O DOMINGO"));
		}
		return errors;
	}
	
	/**
	 * Crea un nuevo plato
	 * 
	 * @param plat plato a crear en la bd
	 * @return Plato creado
	 */
	@Override
	@Transactional
	public Plato create(Plato plato) throws PlatoDomainException {
		List<PlatoError> errors = validateCreatePlato(plato);
		if (!errors.isEmpty()) {
			throw new PlatoDomainException(errors);
		}
		return platoDao.save(plato);
	}

	/**
	 * Valida que los atributos del plato sean correctos segun la logica del negocio.
	 * @param plato
	 * @return
	 */
	private List<PlatoError> validateCreatePlato(Plato plato) {
		List<PlatoError> errors = new ArrayList<>();
		if(plato.getRest_id()<0) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_NUMBER, "Rest_id","El Rest_id debe ser mayor a 0"));
		}
		if (plato.getPrecio()==null) {
			plato.setPrecio(0L);
		}else if(plato.getPrecio()<0L) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_NUMBER, "Precio","El precio no puede ser negativo"));
		}
		if(plato.getCantidad()==null) {
			plato.setCantidad(0L);
		}else if(plato.getCantidad()<0L) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_NUMBER, "Cantidad","La cantidad no puede ser negativa"));
		}
		
		if(plato.getUrl_imagen()==null) {
			plato.setUrl_imagen("src/main/java/resources/sincaratula.jpg");
			
		}else if(plato.getUrl_imagen().equals("")) {
			plato.setUrl_imagen("src/main/java/resources/sincaratula.jpg");
			
		}
		
		if(plato.getDia()==null) {
			errors.add(new PlatoError(EnumErrorCodes.NULL, "Dia","El dia debe ser diferente de nulo"));
		}else if(!validateDomainDia(plato.getDia())) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_DOMAIN, "Dia","El campo dia debe ser de tipo: "
					+ "LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO O DOMINGO"));
		}
		try {
			Plato p = findPlato(plato.getRest_id(), plato.getDia());
			if (p!=null) {
				errors.add(new PlatoError(EnumErrorCodes.FOUND, "Plato","El plato con el Rest_id y dia determinado ya existe."));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return errors;
	}
	
	/**
	 * Modifica o edita un plato
	 * 
	 * @param id,     identificador del plato a modificar
	 * @param plato plato con los datos a editar
	 * @return Plato modificado.
	 */
	@Override
	@Transactional
	public Plato update(Long idRest,String dia,Plato plato) throws PlatoDomainException{
		Plato platUpdate = this.findPlato(idRest,dia);
		List<PlatoError> errors = validateUpdatePlato(plato);
		if (!errors.isEmpty()) {
			throw new PlatoDomainException(errors);
		}

		platUpdate.setDescripcion(plato.getDescripcion());
		platUpdate.setPrecio(plato.getPrecio());
		platUpdate.setCantidad(plato.getCantidad());
		platUpdate.setUrl_imagen(plato.getUrl_imagen());
		return platoDao.save(platUpdate);
	}

	/**
	 * Valida que los atributos del plato a actualizar sean correctos, segun la logica del negocio.
	 * @param plato
	 * @return
	 */
	private List<PlatoError> validateUpdatePlato(Plato plato) {
		List<PlatoError> errors = new ArrayList<>();
		if(plato.getRest_id()<0) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_NUMBER, "Rest_id","El Rest_id debe ser mayor a 0"));
		}
		if (plato.getPrecio()==null) {
			plato.setPrecio(0L);
		}else if(plato.getPrecio()<0) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_NUMBER, "Precio","El precio no puede ser negativo"));
		}
		if(plato.getCantidad()==null) {
			plato.setCantidad(0L);
		}else if(plato.getCantidad()<0) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_NUMBER, "Cantidad","La cantidad no puede ser negativa"));
		}
		if(plato.getDescripcion()==null) {
			plato.setDescripcion("");
		}
		if(plato.getDia()==null) {
			errors.add(new PlatoError(EnumErrorCodes.NULL, "Dia","El dia debe ser diferente de nulo"));
		}else if(!validateDomainDia(plato.getDia())) {
			errors.add(new PlatoError(EnumErrorCodes.INVALID_DOMAIN, "Dia","El campo dia debe ser de tipo: "
					+ "LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO O DOMINGO"));
		}
		return errors;
	}

	/**
	 * Eliminar plato por su id
	 * 
	 * @param id identificador del plato a eliminar
	 */
	@Override
	@Transactional
	public void deletePlato(Long idRest,String dia)throws PlatoDomainException{
		Plato plat = this.findPlato(idRest,dia);
		platoDao.deleteById(plat.getIdPlato());
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
