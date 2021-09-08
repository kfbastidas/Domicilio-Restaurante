package co.mycompany.Componente.access.dao;


import co.mycompany.Componente.domain.entity.RestComponente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
/**
 * Interfaz DAO de Restuarante que extiende CrudRepository
 * @author Kevith Felipe Bastidas
 */

public interface IRestComponenteDao extends CrudRepository<RestComponente, Long> {
	@Query(value = "Select c.idComp from RESTCOMPONENTE c where c.idrest = ?1 and c.dia = ?2",nativeQuery = true)
	public List<Long> findIdComponentes(Long idRest,String dia);
	
	@Query(value = "Select * from RESTCOMPONENTE c where c.idrest = ?1 and c.idcomp=?2 and c.dia = ?3",nativeQuery = true)
	public RestComponente findComponente(Long idRest,Long idComp,String dia);
}