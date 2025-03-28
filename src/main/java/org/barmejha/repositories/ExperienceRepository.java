package org.barmejha.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Experience;

import java.util.List;

@ApplicationScoped
public class ExperienceRepository implements PanacheRepository<Experience> {
  public List<Experience> findByActivityId(Long activityId) {
    return list("activity.id", activityId);
  }
}