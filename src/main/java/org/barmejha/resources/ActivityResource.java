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
import org.barmejha.domain.entities.Activity;
import org.barmejha.repositories.ActivityRepository;

import java.util.List;

@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class ActivityResource {

  private final ActivityRepository activityRepository;

  @GET
  public List<Activity> getAllActivities() {
    return activityRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Activity getActivityById(@PathParam("id") Long id) {
    return activityRepository.findById(id);
  }

  @POST
  @Transactional
  public Response createActivity(Activity activity) {
    activityRepository.persist(activity);
    return Response.status(201).entity(activity).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Activity updateActivity(@PathParam("id") Long id, Activity updatedActivity) {
    Activity activity = activityRepository.findById(id);
    if (activity != null) {
      activity.name = updatedActivity.name;
      activity.description = updatedActivity.description;
      activity.duration = updatedActivity.duration;
      activity.basePrice = updatedActivity.basePrice;
      activity.discountPrice = updatedActivity.discountPrice;
    }
    return activity;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deleteActivity(@PathParam("id") Long id) {
    activityRepository.deleteById(id);
  }
}