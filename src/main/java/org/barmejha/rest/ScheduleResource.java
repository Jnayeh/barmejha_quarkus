package org.barmejha.rest;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.barmejha.domain.dtos.ScheduleDTO;
import org.barmejha.domain.entities.Schedule;
import org.barmejha.rest.interfaces.AbstractEntityResource;
import org.barmejha.services.ScheduleService;

import java.util.List;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@ApplicationScoped
public class ScheduleResource extends AbstractEntityResource<Schedule, ScheduleDTO> {
  private final ScheduleService scheduleService;

  @GET
  @Path("/activity/{id}")
  public Uni<List<ScheduleDTO>> getAllSchedulesByActivity(@PathParam("id") Long id) {
    return scheduleService.getAllSchedulesByActivity(id);
  }
}