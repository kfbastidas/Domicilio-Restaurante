package co.mycompany.Componente.domain.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import co.mycompany.Componente.domain.entity.RestComponente;

@FeignClient(name = "service-restcomponente", url = "localhost:8003")
public interface IRestComponenteClientRest {
	@GetMapping("/restcomponente/{id}/{dia}/")
	public List<Long> findComponentes(@PathVariable Long id,@PathVariable String dia);

	@PostMapping("/restcomponente/")
	public RestComponente createComponenteDia(@RequestBody RestComponente restComp);

	@DeleteMapping("/restcomponente/{idRest}/{idComp}/{dia}/")
	public void delete(@PathVariable Long idRest,@PathVariable Long idComp,@PathVariable String dia);
}
