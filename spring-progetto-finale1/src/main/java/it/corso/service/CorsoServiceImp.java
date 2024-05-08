package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import it.corso.dao.CategoriaDAO;
import it.corso.dao.CorsoDAO;
import it.corso.dto.CorsoDTO;
import it.corso.dto.CorsoRegistrazioneDTO;
import it.corso.model.Categoria;
import it.corso.model.Corso;

@Service
public class CorsoServiceImp implements CorsoService {

	@Autowired
	private CorsoDAO corsoDao;
	
	@Autowired
	private CategoriaDAO categoriaDao;
	
	private ModelMapper mapper = new ModelMapper();
	

	@Override
 	public List<CorsoDTO> getCourses() {
  
		List<Corso> corso = (List<Corso>) corsoDao.findAll();
		List<CorsoDTO> corsoDto = new ArrayList<>();
		corso.forEach(c -> corsoDto.add(mapper.map(c, CorsoDTO.class)));
  
		return corsoDto;
	}

	@Override
	public void deleteCorso(int id) {
		Optional<Corso> corsoDb = corsoDao.findById(id);
		if (corsoDb.isPresent()) {
			corsoDao.delete(corsoDb.get());
		}
	}
	
	@Override
	public void createCorso(CorsoRegistrazioneDTO corsoRegistrazioneDto) {
	//Optional<Categoria> categoriaDb = categoriaDao.findById(corsoRegistrazioneDto.getIdCategoria());
	//if(categoriaDb.isPresent()) {
	//Categoria categoria = categoriaDb.get();

	Corso corso = new Corso();
	corso.setNomeCorso(corsoRegistrazioneDto.getNomeCorso());
	corso.setDescrizioneBreve(corsoRegistrazioneDto.getDescrizioneBreve());
	corso.setDescrizioneCompleta(corsoRegistrazioneDto.getDescrizioneCompleta());
	corso.setDurata(corsoRegistrazioneDto.getDurata());
	//corso.setCategoria(categoria);

	corsoDao.save(corso);
	}

	
}


