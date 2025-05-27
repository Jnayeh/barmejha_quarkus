package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.User;
import org.barmejha.rest.abstraction.AbstractEntityResource;
import org.barmejha.services.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource extends AbstractEntityResource<User, UserDTO> {
  public UserResource(UserService inheritedEntityService) {
    super(inheritedEntityService);
  }
}