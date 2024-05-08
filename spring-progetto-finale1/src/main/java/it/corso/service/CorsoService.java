package it.corso.service;

import java.util.List;

import it.corso.dto.CorsoDTO;
import it.corso.dto.CorsoRegistrazioneDTO;

public interface CorsoService {

	List<CorsoDTO> getCourses();

	void deleteCorso(int id);

	void createCorso(CorsoRegistrazioneDTO corsoRegistrazioneDto);

}
