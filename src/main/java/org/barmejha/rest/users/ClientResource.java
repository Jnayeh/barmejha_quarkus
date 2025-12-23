package org.barmejha.rest.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.Client;
import org.barmejha.rest.abstraction.AbstractEntityResource;
import org.barmejha.services.users.ClientService;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ClientResource extends AbstractEntityResource<Client, UserDTO> {
  public ClientResource(ClientService inheritedEntityService) {
    super(inheritedEntityService);
  }
}