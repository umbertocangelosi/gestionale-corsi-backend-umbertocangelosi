package it.corso.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;

public class UtenteRegistrazioneDTO {
	
	@Pattern(regexp = "[a-zA-Z\\s']{5,50}")
	private String nome;
	@Pattern(regexp = "[a-zA-Z\\s']{1,50}")
	private String cognome;
	@Pattern(regexp = "[a-z0-9\\.\\+_-]+@[A-z0-9\\._-]+[A-z]{2,6}")
	private String email;

	private String password;
	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
