package org.barmejha.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Schedule;

import java.util.List;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<Schedule> {
  public List<Schedule> findByActivityId(Long activityId) {
    return list("activity.id", activityId);
  }
}