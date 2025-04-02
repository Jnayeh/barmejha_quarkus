package org.barmejha.repositories;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Schedule;
import org.barmejha.repositories.generic.GenericRepository;

import java.util.List;

@ApplicationScoped
public class ScheduleRepository extends GenericRepository<Schedule> {
  public Uni<List<Schedule>> findByActivityId(Long activityId) {
    return list("activity.id", activityId);
  }

  @Override
  protected String getEntityName() {
    return "schedules e";
  }
}