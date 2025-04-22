package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.barmejha.domain.dtos.LocationDTO;
import org.barmejha.domain.entities.Location;
import org.barmejha.rest.interfaces.AbstractEntityResource;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@ApplicationScoped
public class LocationResource extends AbstractEntityResource<Location, LocationDTO> {
}