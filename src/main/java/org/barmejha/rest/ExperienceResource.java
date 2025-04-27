package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.ExperienceDTO;
import org.barmejha.domain.entities.Experience;
import org.barmejha.rest.interfaces.AbstractEntityResource;
import org.barmejha.services.ExperienceService;

@Path("/experiences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ExperienceResource extends AbstractEntityResource<Experience, ExperienceDTO> {

  public ExperienceResource(ExperienceService inheritedEntityService) {
    super(inheritedEntityService);
  }
}