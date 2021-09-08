package co.mycompany.Componente.domain.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.mycompany.Componente.domain.entity.Componente;

/**
 * Interfaz DAO de Componente que extiende CrudRepository
 * @author kevith Felipe Bastidas
 *
 */
@FeignClient(name = "servicio-componente", url = "localhost:8001")
public interface IComponenteClientRest{
	@GetMapping("/componente/")
	public List<Componente> list();
	
	@GetMapping("/componente/{id}")
	public Componente detail(@PathVariable Long id);

	@PostMapping("/componente/")
	public Componente create(@RequestBody Componente componente);

	@PutMapping("/componente/{id}")
	public Componente update(@RequestBody Componente componente, @PathVariable Long id);

	@DeleteMapping("/componente/{id}")
	public void delete(@PathVariable Long id);
}
