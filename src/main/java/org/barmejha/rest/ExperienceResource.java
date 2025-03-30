package org.barmejha.rest;

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
import org.barmejha.domain.entities.Experience;
import org.barmejha.repositories.ExperienceRepository;

import java.util.List;

@Path("/experiences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class ExperienceResource {

  private final ExperienceRepository experienceRepository;

  @GET
  public List<Experience> getAllExperiences() {
    return experienceRepository.listAll();
  }

  @GET
  @Path("/activity/{id}")
  public List<Experience> getAllExperiencesByActivity(@PathParam("id") Long id) {
    return experienceRepository.findByActivityId(id);
  }

  @GET
  @Path("/{id}")
  public Experience getExperienceById(@PathParam("id") Long id) {
    return experienceRepository.findById(id);
  }

  @POST
  @Transactional
  public Response createExperience(Experience experience) {
    experienceRepository.persist(experience);
    return Response.status(201).entity(experience).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Experience updatedExperience(@PathParam("id") Long id, Experience updatedExperience) {
    Experience experience = experienceRepository.findById(id);
    if (experience != null) {
      experience.setMedia(updatedExperience.getMedia());
      experience.setRating(updatedExperience.getRating());
      experience.setReview(updatedExperience.getReview());
    }
    return experience;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deleteExperience(@PathParam("id") Long id) {
    experienceRepository.deleteById(id);
  }
}