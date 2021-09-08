package co.mycompany.Componente.access.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.mycompany.Componente.domain.entity.Componente;


/**
 * Interfaz DAO de Componente que extiende CrudRepository
 * @author kevith Felipe Bastidas
 *
 */
public interface IComponenteDao extends CrudRepository<Componente, Long>{
	@Query(value = "Select * from COMPONENTE c where c.id = ?1",nativeQuery = true)
	public Componente findByIdComponente(Long id);
	
	@Query(value = "SELECT * FROM COMPONENTE C WHERE C.NOMBRE = ?1", nativeQuery = true)
	public Componente findByName(String nombre);
	
}
