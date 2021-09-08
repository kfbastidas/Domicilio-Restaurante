package co.mycompany.Plato.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.mycompany.Plato.domain.entity.Plato;
import co.mycompany.Plato.domain.service.IPlatoService;
import co.mycompany.Plato.presentation.rest.exceptions.PlatoDomainException;

import org.springframework.http.HttpStatus;

/**
 * Servicios web de platos
 * 
 * @author Kevith Felipe Bastidas
 *
 */
@RestController
@RequestMapping("plato")
public class PlatoController {
	@Autowired
	private IPlatoService platoService;

	/**
	 * Encuentra un plato de un id de restaurante en un dia determinado
	 * @param idRest
	 * @param dia
	 * @return
	 * @throws PlatoDomainException
	 */
	@RequestMapping(value = "{idRest}/{dia}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Plato findPlato(@PathVariable Long idRest,@PathVariable String dia) throws PlatoDomainException {
		return platoService.findPlato(idRest, dia);
	}	
	
	/**
	 * Crea un plato
	 * @param plat
	 * @return
	 * @throws PlatoDomainException
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Plato create(@RequestBody Plato plat) throws PlatoDomainException {
		return platoService.create(plat);
	}

	/**
	 * Actualiza un plato de un id de restaurante en un dia determinado
	 * @param idRest
	 * @param dia
	 * @param plat
	 * @return
	 * @throws PlatoDomainException
	 */
	@RequestMapping(value = "{idRest}/{dia}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Plato update(@PathVariable Long idRest,@PathVariable String dia, @RequestBody Plato plat) throws PlatoDomainException {
		return platoService.update(idRest, dia, plat);
	}

	/**
	 * Elimina un plato
	 * @param idRest
	 * @param dia
	 * @throws PlatoDomainException
	 */
	@RequestMapping(value = "{idRest}/{dia}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idRest,@PathVariable String dia) throws PlatoDomainException {
		platoService.deletePlato(idRest, dia);;
	}
}