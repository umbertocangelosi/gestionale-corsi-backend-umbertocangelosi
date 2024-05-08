package it.corso.service;

import java.util.List;

import it.corso.dto.UtenteAggiornamentoDTO;
import it.corso.dto.UtenteDTO;
import it.corso.dto.UtenteLoginRequestDTO;
import it.corso.dto.UtenteRegistrazioneDTO;
import it.corso.dto.UtenteShowDTO;
import it.corso.model.Utente;

public interface UtenteService {

	void insert(UtenteRegistrazioneDTO utenteDTO);
	boolean existsByEmail(String email);
	void deleteUser(String email);
	Utente getUtenteByEmail(String email);
	//void updateUtenteByEmail(UtenteAggiornamentoDTO utenteDTO);
	List<UtenteShowDTO> getUsers();
	boolean loginUtente(UtenteLoginRequestDTO utenteLoginRequestDto);
	void updateUtenteByEmail(UtenteAggiornamentoDTO utenteDTO);
	UtenteDTO getUtenteDTOByEmail(String email);
}
