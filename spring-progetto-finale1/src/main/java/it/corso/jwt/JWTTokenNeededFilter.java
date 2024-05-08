package it.corso.jwt;

import java.io.IOException;
import java.security.Key;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.corso.service.Blacklist;
import jakarta.annotation.Resource;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@JWTTokenNeeded
@Provider
public class JWTTokenNeededFilter implements ContainerRequestFilter {
	// iniezione delle dipendenze con context
	@Context
	private ResourceInfo resourceInfo;
	
	@Autowired
	private Blacklist blackList;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		//controlla se è presente l'annotation nel progetto
		Secured annotationRole = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
			if(annotationRole == null) {
				annotationRole = resourceInfo.getResourceClass().getAnnotation(Secured.class);
				
			}
			//estrae dall'header l'autorizzazione dalla pagina 
			String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			if(header == null || !header.startsWith("Bearer ")) {
				throw new NotAuthorizedException("Authorization Header must be provided");
			}
			
			// estrae il token dalla HTTP Authorization header
			String token = header.substring("Bearer".length()).trim();
			/*
			if(blackList.isTokenRevoked(token)) {
				throw new NotAuthorizedException("Blacklisted Token");
			}
			*/
			try {
				
				byte[] secretKey= "asocbaseisufvnqunauaouaishdfoabf122373573771796798271314".getBytes();
				//crittografia
				Key key = Keys.hmacShaKeyFor(secretKey);
				Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
				Claims body= claims.getBody();
				List<String> rolesToken = body.get("ruoli", List.class);
				Boolean hasRole=false;
				
				// facciamo un controllo per verificare se il ruolo dell'annotation è contenuto dentro la lista dei ruoli
				for (String role : rolesToken) {
					
					if(role.equals(annotationRole.role())) {
						
						hasRole=true;
					}
				}
				if(!hasRole) {
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
				}
				
				}catch(Exception e) {
				
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}	
	}	
}
/*
 * 
@Override
 public void deleteUser(String email) {

  Utente utente = utenteDao.findByEmail(email);
  
  int id = utente.getId();
  Optional<Utente> userOptional = utenteDao.findById(id);
  if (userOptional.isPresent()) {

   utenteDao.delete(userOptional.get());
  }
 }





@DELETE
 @Path("delete/{email}")
 public Response deleteUser(@PathParam("email") String email) {
  
  try {
   utenteService.deleteUser(email);
   
   return Response.status(Response.Status.OK).build();
  } catch (Exception e) {

   return Response.status(Response.Status.BAD_REQUEST).build();
  }
  
 }
 





@PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
 public Response updateUser(@RequestBody UtenteDtoAggiornamento utente) {

  
  try {
   
   utenteService.updateUserData(utente);
   
   return Response.status(Response.Status.OK).build();
   
  } catch (Exception e) {
   
   return Response.status(Response.Status.BAD_REQUEST).build();
  }
  
 }
*/
