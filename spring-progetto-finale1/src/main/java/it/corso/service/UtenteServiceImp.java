package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.model.Model;
import it.corso.dao.RuoloDAO;
import it.corso.dao.UtenteDAO;
import it.corso.dto.UtenteAggiornamentoDTO;
import it.corso.dto.UtenteDTO;
import it.corso.dto.UtenteLoginRequestDTO;
import it.corso.dto.UtenteRegistrazioneDTO;
import it.corso.dto.UtenteShowDTO;
import it.corso.model.Ruolo;
import it.corso.model.Utente;

@Service
public class UtenteServiceImp implements UtenteService {

	@Autowired
	private UtenteDAO utenteDao;
	
	@Autowired
	private RuoloDAO ruoloDao;
	

	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public void insert(UtenteRegistrazioneDTO utenteDTO) {
		
		Utente utente = new Utente();
		
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setEmail(utenteDTO.getEmail());
		
		String sha256hex=DigestUtils.sha256Hex(utenteDTO.getPassword());
		utente.setPassword(sha256hex);
		
		utenteDao.save(utente);
	}
	
	@Override
	public boolean existsByEmail(String email) {
		return utenteDao.existsByEmail(email);
	}

	@Override
	public void deleteUser(String email) {
		//utenteDao.deleteByEmail(email);
		
		Utente utente = utenteDao.findByEmail(email);
		int id=utente.getId();
		
		Optional<Utente> utenteOption = utenteDao.findById(id);
		
		if(utenteOption.isPresent()) {
			utenteDao.delete(utenteOption.get());
		}
	}	
		

	@Override
	public UtenteDTO getUtenteDTOByEmail(String email) {
	
		Utente utente = utenteDao.findByEmail(email);
		return mapper.map(utente, UtenteDTO.class);
		
	}
		
	
	
	@Override
	public void updateUtenteByEmail(UtenteAggiornamentoDTO utenteDTO) {

		String email = utenteDTO.getEmail();
		Utente utente = utenteDao.findByEmail(email);
		if (utente != null) {
			utente.setNome(utenteDTO.getNome());
			utente.setCognome(utenteDTO.getCognome());
			utente.setEmail(utenteDTO.getEmail());

			List<Ruolo> ruoliUtente = new ArrayList<>();

			Optional<Ruolo> ruoloDb = ruoloDao.findById(utenteDTO.getIdRuolo());
			if (ruoloDb.isPresent()) {

				Ruolo ruolo = ruoloDb.get();
				//ruolo.setId(utenteDTO.getIdRuolo());
				ruoliUtente.add(ruolo);
				utente.setRuoli(ruoliUtente);

			}
			utenteDao.save(utente);
		}
	}

	@Override
	public List<UtenteShowDTO> getUsers() {
		
		List<Utente> listaUtenti = (List<Utente>) utenteDao.findAll();
		
		List<UtenteShowDTO> listaUtentiDTO = new ArrayList<>();
		
		listaUtenti.forEach(u-> listaUtentiDTO.add(mapper.map(u,UtenteShowDTO.class)));
		
		return listaUtentiDTO;
	}

	@Override
	public boolean loginUtente(UtenteLoginRequestDTO utenteLoginRequestDto) {
		Utente utente = new Utente();
		System.out.println("1");
		utente.setEmail(utenteLoginRequestDto.getEmail());
		System.out.println("2");
		utente.setPassword(utenteLoginRequestDto.getPassword());
		System.out.println("3");
		String sha256hex=DigestUtils.sha256Hex(utente.getPassword());
		System.out.println(sha256hex);
		//tramite il getPassword di utente recupero la password e la passo al metodo che me la hasha
		Utente credenzialiUtente = utenteDao.findByEmailAndPassword(utente.getEmail(), sha256hex);
		System.out.println(credenzialiUtente.getNome());
		System.out.println("5");
		// operatore ternario
		return credenzialiUtente != null ? true : false;
		
	}

	@Override
	public Utente getUtenteByEmail(String email) {
		return utenteDao.findByEmail(email);
		
	}

	
	
}	
