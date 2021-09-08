package co.mycompany.restaurante.access.dao;


import org.springframework.data.repository.CrudRepository;

import co.mycompany.restaurante.domain.entity.Restaurante;

/**
 * Interfaz DAO de Restuarante que extiende CrudRepository
 * @author Kevith Felipe Bastidas
 */

public interface IRestauranteDao extends CrudRepository<Restaurante, Long> {

}