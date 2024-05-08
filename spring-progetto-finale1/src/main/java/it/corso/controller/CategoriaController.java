package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.corso.dto.CategoriaDTO;
import it.corso.dto.CreateCategoriaDTO;
import it.corso.dto.UtenteDTO;
import it.corso.service.CategoriaService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	
	@GET
	@Path("/get-categoria")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoria(@QueryParam("id_categoria") int id) {
		try {
			CategoriaDTO categoria = categoriaService.getCategoriaByID(id);
			if(categoria != null) {
				return Response.status(Response.Status.OK).entity(categoria).build();
			}
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}	
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insert-categoria")
	public Response insertCategoria(CreateCategoriaDTO categoriaDto) {
		if (categoriaDto != null) {
			try {
				categoriaService.insertCategoria(categoriaDto);
				return Response.status(Response.Status.OK).build();
			} catch (Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-categorie")
	public Response getCategories(@QueryParam("nome_categoria") String nome_categoria) {
		try {
			List<CategoriaDTO> categorie = categoriaService.getAllCategories(nome_categoria);
			return Response.status(Response.Status.OK).entity(categorie).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-categorie")
	public Response getCategories(@QueryParam("id") int id) {
		try {
			List<CategoriaDTO> categorie = categoriaService.getAllCategories(id);
			return Response.status(Response.Status.OK).entity(categorie).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
*/
}
