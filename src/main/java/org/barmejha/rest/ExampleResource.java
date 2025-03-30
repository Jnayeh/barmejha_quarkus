package org.barmejha.rest;

import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barmejha.domain.entities.Activity;
import org.barmejha.domain.entities.Payment;
import org.barmejha.repositories.ActivityRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
  @Path("/io-test")
  @Produces(MediaType.APPLICATION_JSON)

  public Uni<Map<String, String>> helloJson() {
    var h = new HashMap<String, String>();
    h.put("hello", "Hello from Quarkus REST");
    return Uni.combine().all().unis(
        Uni.createFrom().item("slowest()").onItem().delayIt().by(Duration.ofMillis(2000))
            .map(t -> slowest()),
        Uni.createFrom().item("fast()").onItem().delayIt().by(Duration.ofMillis(500))
            .map(t -> fast()),
        Uni.createFrom().item("slower()").onItem().delayIt().by(Duration.ofMillis(1000))
            .map(t -> slower())
    ).asTuple()
        .map(t -> h);
  }

  private static String slowest() {
    log.warn("Hello from slowest REST");
          return "";
  }

  private static String slower() {
    log.warn("Hello from slower REST");
    return "";
  }

  private static String fast() {
    log.warn("Hello from fast REST");
    return "";
  }

  @Tag(name = "add activity")
  @POST
  @Path("/entities/add")
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)

  public List<Activity> helloJson(Activity myEntity) {
    myEntity.persistAndFlush();
    var activities = activityRepository.findAll(Sort.by("id")).stream().toList();
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
    return Payment.listAll();
  }
}
