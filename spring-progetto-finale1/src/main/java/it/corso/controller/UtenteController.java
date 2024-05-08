package it.corso.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import java.security.Key;
import java.time.LocalDateTime;

import it.corso.dto.UtenteAggiornamentoDTO;
import it.corso.dto.UtenteDTO;
import it.corso.dto.UtenteLoginRequestDTO;
import it.corso.dto.UtenteLoginResponseDTO;
import it.corso.dto.UtenteRegistrazioneDTO;
import it.corso.dto.UtenteShowDTO;
import it.corso.jwt.JWTTokenNeeded;
import it.corso.jwt.Secured;
import it.corso.model.Ruolo;
import it.corso.model.Utente;
import it.corso.service.Blacklist;
import it.corso.service.UtenteService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Path("/utenti") //prima utente
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private Blacklist blackList;
	
	@POST
	@Path("/registrazione")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userRegistration(@Valid @RequestBody UtenteRegistrazioneDTO utenteDTO) {
		
		try {
			if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", utenteDTO.getPassword())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			if(utenteService.existsByEmail(utenteDTO.getEmail())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			utenteService.insert(utenteDTO);
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			System.err.println(e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE	
	@Path("/{email}")
	public Response deleteUserByEmail(@PathParam("email") String email) {
		
		try {
			utenteService.deleteUser(email);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			System.err.println(e);
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		/**
		try {
			if(utenteService.existsByEmail(email)) {
				utenteService.deleteUser(email);
				return Response.status(Response.Status.OK).build();
			}
			utenteService.deleteUser(email);
			return Response.status(Response.Status.BAD_REQUEST).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		*/
	 
	}

	@GET
	@Path("utente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selezionaUtenteByEmail(@QueryParam("email") String email) {
	    try {
	        if (email != null && !email.isEmpty()) {
	            // Se l'email non è vuota o nulla, chiamiamo il servizio per ottenere l'utente
	            UtenteDTO utenteDto = utenteService.getUtenteDTOByEmail(email);
	            // Restituiamo una risposta OK con l'oggetto UtenteDTO come corpo della risposta
	            return Response.ok().entity(utenteDto).build();
	        }
	        // Se l'email è vuota o nulla, restituiamo una risposta BAD_REQUEST
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    } catch (Exception e) {
	        // Se si verifica un'eccezione durante l'esecuzione, restituiamo una risposta BAD_REQUEST
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUtente(UtenteAggiornamentoDTO utente) {
		try {
			utenteService.updateUtenteByEmail(utente);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();		}
	}
	
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUsers() {
		try {
			List<UtenteShowDTO> utenti = utenteService.getUsers();
			return Response.ok().entity(utenti).build();
		} catch (Exception e) {

			Response.status(Response.Status.BAD_REQUEST).build();
		}

		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginUser(UtenteLoginRequestDTO utenteLoginRequestDto){
		try {
			if(utenteService.loginUtente(utenteLoginRequestDto)) {
				return Response.ok(issueToken(utenteLoginRequestDto.getEmail())).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	
	
	private UtenteLoginResponseDTO issueToken(String email){
		// eseguiamo una cifratura attraverso l'algoritmo di crittografia HMAC
		byte[] secretKey= "asocbaseisufvnqunauaouaishdfoabf122373573771796798271314".getBytes();
		
		//crittografia
		Key key = Keys.hmacShaKeyFor(secretKey);
		
		Utente utente = utenteService.getUtenteByEmail(email);
		
		//claims (informazioni)
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("nome",utente.getNome());
		map.put("cognome",utente.getCognome());
		
		List<String> ruoli = new ArrayList<>();
		
		for(Ruolo ruolo : utente.getRuoli()) {
			ruoli.add(ruolo.getTipologia().name());
			
		}
		map.put("ruoli", ruoli);
		
		//data di creazione ed espirazione
		Date creationDate = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		// craezione del token firmato con la chiave segreta creata sopra
		String jwtToken = Jwts.builder()
			.setClaims(map) //cosa
			.setIssuer("http://localhost:8080") //dove
			.setIssuedAt(creationDate) //quando viene creato
			.setExpiration(end) //quando finisce
			.signWith(key) //firma con la mia classe segreta 
			.compact(); //compattiamo il token
		
		UtenteLoginResponseDTO token = new UtenteLoginResponseDTO();
		
		token.setToken(jwtToken);
		token.setTokenCreationTime(creationDate);
		token.setTtl(end);
		
		return token;
	}

	
	
	
}
