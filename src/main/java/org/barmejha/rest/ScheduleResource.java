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
import org.barmejha.domain.entities.Schedule;
import org.barmejha.repositories.ScheduleRepository;

import java.util.List;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class ScheduleResource {

  private final ScheduleRepository scheduleRepository;

  @GET
  public List<Schedule> getAllSchedules() {
    return scheduleRepository.listAll();
  }

  @GET
  @Path("/activity/{id}")
  public List<Schedule> getAllSchedulesByActivity(@PathParam("id") Long id) {
    return scheduleRepository.findByActivityId(id);
  }

  @GET
  @Path("/{id}")
  public Schedule getScheduleById(@PathParam("id") Long id) {
    return scheduleRepository.findById(id);
  }

  @POST
  @Transactional
  public Response createSchedule(Schedule schedule) {
    scheduleRepository.persist(schedule);
    return Response.status(201).entity(schedule).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Schedule updateSchedule(@PathParam("id") Long id, Schedule updatedSchedule) {
    Schedule schedule = scheduleRepository.findById(id);
    if (schedule != null) {
      schedule.setStartDate(updatedSchedule.getStartDate());
      schedule.setEndDate(updatedSchedule.getEndDate());
      schedule.setRecurrence(updatedSchedule.getRecurrence());
      schedule.setRecurringDays(updatedSchedule.getRecurringDays());
      schedule.setStartTime(updatedSchedule.getStartTime());
      schedule.setEndTime(updatedSchedule.getEndTime());
      schedule.setBreaks(updatedSchedule.getBreaks());
      schedule.setExcludedDates(updatedSchedule.getExcludedDates());
    }
    return schedule;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deleteSchedule(@PathParam("id") Long id) {
    scheduleRepository.deleteById(id);
  }
}