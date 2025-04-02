package org.barmejha.rest;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.barmejha.domain.entities.Schedule;
import org.barmejha.rest.interfaces.IEntityResource;
import org.barmejha.services.ScheduleService;

import java.util.List;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class ScheduleResource extends IEntityResource<Schedule> {
  private final ScheduleService scheduleService;

  @GET
  @Path("/activity/{id}")
  public Uni<List<Schedule>> getAllSchedulesByActivity(@PathParam("id") Long id) {
    return scheduleService.getAllSchedulesByActivity(id);
  }
}