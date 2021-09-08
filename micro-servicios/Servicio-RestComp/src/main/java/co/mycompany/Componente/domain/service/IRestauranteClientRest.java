package co.mycompany.Componente.domain.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import co.mycompany.Componente.domain.entity.Restaurante;

/**
 * 
 * @author Kevith Felipe Bastidas
 *
 */
@FeignClient(name = "servicio-restaurante", url = "localhost:8002")
public interface IRestauranteClientRest {
	
	@GetMapping("/restaurante/")
	public List<Restaurante> list();
	
	@GetMapping("/restaurante/{id}")
	public Restaurante detail(@PathVariable Long id);

	@PostMapping("/restaurante/")
	public Restaurante create(@RequestBody Restaurante restaurante);

	@PutMapping("/restaurante/{id}")
	public Restaurante update(@RequestBody Restaurante restaurante, @PathVariable Long id);

	@DeleteMapping("/restaurante/{id}")
	public void delete(@PathVariable Long id);
	
}
