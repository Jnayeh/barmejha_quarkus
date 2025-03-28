package org.barmejha.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.barmejha.domain.entities.Location;
import org.barmejha.repositories.LocationRepository;

import java.util.List;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class LocationResource {

  private final LocationRepository locationRepository;

  @GET
  public List<Location> getAllLocations() {
    return locationRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Location getLocationById(@PathParam("id") Long id) {
    return locationRepository.findById(id);
  }

  @POST
  @Transactional
  public Response createLocation(Location location) {
    locationRepository.persist(location);
    return Response.status(201).entity(location).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Location updateLocation(@PathParam("id") Long id, Location updatedLocation) {
    Location location = locationRepository.findById(id);
    if (location != null) {
      location.name = updatedLocation.name;
      location.address = updatedLocation.address;
      location.latitude = updatedLocation.latitude;
      location.longitude = updatedLocation.longitude;
    }
    return location;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deleteLocation(@PathParam("id") Long id) {
    locationRepository.deleteById(id);
  }
}