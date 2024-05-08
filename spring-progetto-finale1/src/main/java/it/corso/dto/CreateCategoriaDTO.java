package it.corso.dto;

import it.corso.model.NomeCategoria;
import it.corso.model.Tipologia;

public class CreateCategoriaDTO {

	private NomeCategoria nome_categoria;

	public NomeCategoria getNome_categoria() {
		return nome_categoria;
	}

	public void setNome_categoria(NomeCategoria nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
	
	
}
