package it.corso.service;

import java.util.List;

import it.corso.dto.CategoriaDTO;
import it.corso.dto.CreateCategoriaDTO;
import it.corso.model.Categoria;

public interface CategoriaService {



	void insertCategoria(CreateCategoriaDTO categoria);
	CategoriaDTO getCategoriaByID(int id);

	//List<CategoriaDTO> getAllCategories();
	//List<CategoriaDTO> getAllCategories(Integer id)
	List<CategoriaDTO> getAllCategories(String nome_parametro);
}
