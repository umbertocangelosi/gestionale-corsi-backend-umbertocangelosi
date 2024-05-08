package it.corso.dto;

import it.corso.model.NomeCategoria;


public class CategoriaDTO {

	private int id;
	//potrei implementare la validazione con @Pattern
	private NomeCategoria nomeCategoria;
	
	
	
	public CategoriaDTO(int id2, NomeCategoria nomeCategoria2) {
		// TODO Auto-generated constructor stub
	}
	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public NomeCategoria getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(NomeCategoria nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	
	
}
