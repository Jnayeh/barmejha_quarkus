package org.barmejha.rest.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.rest.abstraction.AbstractEntityResource;
import org.barmejha.services.users.ProviderService;

@Path("/providers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProviderResource extends AbstractEntityResource<Provider, UserDTO> {
  public ProviderResource(ProviderService inheritedEntityService) {
    super(inheritedEntityService);
  }
}