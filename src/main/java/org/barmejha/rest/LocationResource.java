package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.LocationDTO;
import org.barmejha.domain.entities.Location;
import org.barmejha.rest.interfaces.AbstractEntityResource;
import org.barmejha.services.LocationService;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LocationResource extends AbstractEntityResource<Location, LocationDTO> {
  public LocationResource(LocationService inheritedEntityService) {
    super(inheritedEntityService);
  }
}