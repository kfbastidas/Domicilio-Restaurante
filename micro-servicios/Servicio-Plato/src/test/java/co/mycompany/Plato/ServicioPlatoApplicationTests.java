package co.mycompany.Plato;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.mycompany.Plato.domain.entity.Plato;
import co.mycompany.Plato.domain.service.IPlatoService;

@SpringBootTest
class ServicioPlatoApplicationTests {
	@Autowired
	IPlatoService platoservice;
	
	@Test
	void findPlatoExistente() {
		Long rest_id = 1L;
		String dia = "LUNES";
		Plato p=null;
		try {
			p = platoservice.findPlato(rest_id, dia);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		assertEquals(p.getDescripcion(), "300 g de carne asada al carbon");
		assertEquals(p.getPrecio(), 7000);
		assertEquals(p.getCantidad(), 0);
		assertEquals(p.getRest_id(),rest_id);
		assertEquals(p.getDia(),dia);
	}

	@Test
	void findPlatoNoExistente() {
		Long rest_id = 2L;
		String dia = "LUNES";
		Plato p=null;
		try {
			p = platoservice.findPlato(rest_id, dia);
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(p, null);
	}
	
	@Test
	void findPlatoErroneo() {
		Long rest_id = 2L;
		String dia = "LUNE";
		Plato p=null;
		try {
			p = platoservice.findPlato(rest_id, dia);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		assertEquals(p, null);
	}
	
	@Test
	void createPlato() {
		Plato p = new Plato();
		p.setCantidad(0L);
		p.setDescripcion("hola mundo");
		p.setDia("LUNES");
		p.setPrecio(2000L);
		p.setRest_id(3L);
		Plato pSave = null;
		try {
			pSave = platoservice.create(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(pSave);
		assertEquals(pSave,p);
		
	}
	
	@Test
	void createPlatoExistente() {
		Plato p = new Plato();
		p.setCantidad(0L);
		p.setDescripcion("hola mundo");
		p.setDia("LUNES");
		p.setPrecio(2000L);
		p.setRest_id(1L);
		Plato pSave = null;
		try {
			pSave = platoservice.create(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(pSave,null);
		
	}
	
	@Test
	void createPlatoErroneo() {
		Plato p = new Plato();
		p.setCantidad(-5L);
		p.setDescripcion("");
		p.setDia("VIRNES");
		p.setPrecio(-2000L);
		p.setRest_id(1L);
		Plato pSave = null;
		try {
			pSave = platoservice.create(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(pSave,null);
		
	}
	
	@Test
	void updatePlatoExistente() {
		Long rest_id = 1L;
		String dia = "LUNES";
		
		Plato pEncontrado = null;
		Plato pUpdate = new Plato();
		pUpdate.setCantidad(5L);
		pUpdate.setDescripcion("descripcion actualizada.");
		
		pUpdate.setRest_id(1L);
		try {
			pEncontrado = platoservice.findPlato(rest_id, dia);
			pUpdate.setPrecio(pEncontrado.getPrecio());
			pUpdate = platoservice.update(rest_id, dia,pUpdate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		assertNotEquals(pUpdate.getDescripcion(), pEncontrado.getDescripcion());
		assertNotEquals(pUpdate.getCantidad(), pEncontrado.getCantidad());
		assertEquals(pUpdate.getPrecio(), pEncontrado.getPrecio());
	}
	
	@Test
	void updatePlatoNoExistente() {
		Long rest_id = 9L;
		String dia = "LUNES";

		Plato pUpdate = new Plato();
		pUpdate.setCantidad(5L);
		pUpdate.setDescripcion("descripcion actualizada.");
		pUpdate.setPrecio(7000L);
		pUpdate.setRest_id(1L);
		try {
			pUpdate = platoservice.update(rest_id, dia,pUpdate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		assertNotEquals(pUpdate.getDescripcion(), null);
	}
	
	@Test
	void deletePlatoExistente() {
		Long rest_id = 1L;
		String dia = "JUEVES";

		Plato pDelete = null;
		try {
			pDelete = platoservice.findPlato(rest_id, dia);
			assertNotEquals(pDelete, null);
			platoservice.deletePlato(rest_id, dia);
			pDelete =null;
			pDelete = platoservice.findPlato(rest_id, dia);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(pDelete, null);
	}
	
}
