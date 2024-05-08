package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.CategoriaDAO;
import it.corso.dto.CategoriaDTO;
import it.corso.dto.CreateCategoriaDTO;
import it.corso.model.Categoria;

@Service
public class CategoriaServiceImp implements CategoriaService {
	

	@Autowired
	private CategoriaDAO categoriaDao;

	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public void insertCategoria(CreateCategoriaDTO categoria) {
		// TODO Auto-generated method stub
		Categoria categoriaNew = new Categoria();

		categoriaNew.setNomeCategoria(categoria.getNome_categoria());

		categoriaDao.save(categoriaNew);

	}

	@Override
	public List<CategoriaDTO> getAllCategories(String nome_categoria) {
		List<Categoria> listaCategorie = new ArrayList<>();
		List<CategoriaDTO> listaCategorieDto = new ArrayList<>();

		if (nome_categoria != null) {
			listaCategorie = categoriaDao.getAllByNameCategory(nome_categoria);
		} else {
			listaCategorie = (List<Categoria>) categoriaDao.findAll();
		}

		listaCategorie.forEach(u -> listaCategorieDto.add(mapper.map(u, CategoriaDTO.class)));
		return listaCategorieDto;
	}
	
	
	@Override
	public CategoriaDTO getCategoriaByID(int id) {

		Optional<Categoria> categoriaOptional = categoriaDao.findById(id);
		Categoria categoria = categoriaOptional.get();
		CategoriaDTO categoriaDto = mapper.map(categoria, CategoriaDTO.class);

		return categoriaDto;
	}
	/*
	@Override
	public List<CategoriaDTO> getAllCategories(){
		List<Categoria> listaCategorie = (List<Categoria>) categoriaDao.findAll();
		List<CategoriaDTO> listaCategorieDto =  new ArrayList<>();
		listaCategorie.forEach(u-> listaCategorieDto.add(mapper.map(u,CategoriaDTO.class)));
		return listaCategorieDto;
	}
	*/


}
