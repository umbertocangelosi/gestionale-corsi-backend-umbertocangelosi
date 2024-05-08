package it.corso.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Utente;

public interface UtenteDAO extends CrudRepository<Utente,Integer>{

	boolean existsByEmail(String email);

	void deleteByEmail(String email);
	
	//Optional<Utente> findById(int id);
	
	Utente findByEmail(String email);
	
	Utente findByEmailAndPassword(String email, String password);
}
