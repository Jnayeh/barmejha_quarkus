package org.barmejha.resources;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.barmejha.domain.entities.Activity;
import org.barmejha.domain.entities.Payment;
import org.barmejha.repositories.ActivityRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
@ApplicationScoped
@AllArgsConstructor
public class ExampleResource {

  private final ActivityRepository activityRepository;

  @GET
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello from Quarkus REST";
  }

  @GET
  @Path("/json")
  @Produces(MediaType.APPLICATION_JSON)

  public Map<String, String> helloJson() {
    var h = new HashMap<String, String>();
    h.put("hello", "Hello from Quarkus REST");
    return h;
  }

  @POST
  @Path("/entities/add")
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)

  public List<Activity> helloJson(Activity myEntity) {
    myEntity.persistAndFlush();
    var activities = activityRepository.listAll();
    Log.warn("Activities: " + activities);
    return activities;
  }

  @POST
  @Path("/entities/pay")
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)

  public List<Payment> helloJson(Payment payment) {
    payment.persist();
    return PanacheEntityBase.listAll();
  }
}
