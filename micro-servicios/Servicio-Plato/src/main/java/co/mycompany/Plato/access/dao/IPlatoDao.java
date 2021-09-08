package co.mycompany.Plato.access.dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.mycompany.Plato.domain.entity.Plato;


/**
 * Interfaces DAO de productos
 *@author Kevith Felipe Bastidas
 *
 */
public interface IPlatoDao extends CrudRepository<Plato, Long> {
	
	/**
	 * Metodo de la interfaz IPlatoDao que permite retornar
	 * todso los platos que hacen parte de un restaurante en especifico
	 * @param id. identificador del restaurantes
	 * @return platos del restaurantes
	 */
	@Query("select c from Plato c where c.rest_id = ?1 and c.dia = ?2")
	Plato findPlato(Long idRest,String dia);
	
}