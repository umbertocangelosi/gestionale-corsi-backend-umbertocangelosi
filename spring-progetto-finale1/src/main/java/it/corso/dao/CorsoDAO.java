package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Corso;

public interface CorsoDAO extends CrudRepository<Corso,Integer> {

}
