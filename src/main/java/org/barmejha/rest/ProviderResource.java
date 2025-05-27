package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.domain.entities.users.User;
import org.barmejha.rest.abstraction.AbstractEntityResource;
import org.barmejha.services.ProviderService;
import org.barmejha.services.UserService;

@Path("/providers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProviderResource extends AbstractEntityResource<Provider, UserDTO> {
  public ProviderResource(ProviderService inheritedEntityService) {
    super(inheritedEntityService);
  }
}