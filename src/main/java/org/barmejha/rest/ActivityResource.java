package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.ActivityDTO;
import org.barmejha.domain.entities.Activity;
import org.barmejha.rest.abstraction.AbstractEntityResource;
import org.barmejha.services.ActivityService;

@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@ApplicationScoped
public class ActivityResource extends AbstractEntityResource<Activity, ActivityDTO> {
  public ActivityResource(ActivityService inheritedEntityService) {
    super(inheritedEntityService);
  }
}