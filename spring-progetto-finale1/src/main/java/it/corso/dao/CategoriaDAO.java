package it.corso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.corso.model.Categoria;


public interface CategoriaDAO extends CrudRepository<Categoria,Integer>{

	/*
	@Query
	(value = "Select * from categoria where ID_CA =:p", nativeQuery = true)
	List<Categoria> getAllById(@Param("p") int id);
	*/
	@Query
	(value = "Select * from categoria where Nome_Categoria =:p", nativeQuery = true)
	List<Categoria> getAllByNameCategory(@Param("p") String nome_categoria);
	

}
