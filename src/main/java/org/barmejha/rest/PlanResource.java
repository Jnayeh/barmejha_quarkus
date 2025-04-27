package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.PlanDTO;
import org.barmejha.domain.entities.Plan;
import org.barmejha.rest.interfaces.AbstractEntityResource;
import org.barmejha.services.PlanService;

@Path("/plans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PlanResource extends AbstractEntityResource<Plan, PlanDTO> {
  public PlanResource(PlanService inheritedEntityService) {
    super(inheritedEntityService);
  }
}