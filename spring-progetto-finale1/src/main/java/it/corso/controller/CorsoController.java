package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.ws.rs.core.Response;
import it.corso.dto.CorsoDTO;
import it.corso.dto.CorsoRegistrazioneDTO;
import it.corso.jwt.JWTTokenNeeded;
import it.corso.jwt.Secured;
import it.corso.service.CorsoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

//@Secured(role="Admin")
//@JWTTokenNeeded
@Path("/corsi")
public class CorsoController {

	@Autowired
	private CorsoService corsoService;

	@GET
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourses() {

		try {

			List<CorsoDTO> listaCorsi = corsoService.getCourses();
			return Response.status(Response.Status.OK).entity(listaCorsi).build();

		} catch (Exception e) {

			return Response.status(Response.Status.BAD_REQUEST).entity("Errore caricamento utenti").build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Secured(role = "Admin")
	@JWTTokenNeeded
	public Response deleteCorso(@PathParam("id") int id) {
		try {
			corsoService.deleteCorso(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	
	@POST
	@Path("")
	@Secured(role = "Admin")
	@JWTTokenNeeded
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCorso( CorsoRegistrazioneDTO corsoRegistraioneDto) {
		try {
			corsoService.createCorso(corsoRegistraioneDto);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
