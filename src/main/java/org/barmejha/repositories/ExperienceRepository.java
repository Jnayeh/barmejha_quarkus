package org.barmejha.repositories;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Experience;
import org.barmejha.repositories.generic.GenericRepository;

import java.util.List;

@ApplicationScoped
public class ExperienceRepository extends GenericRepository<Experience> {
  public Uni<List<Experience>> findByActivityId(Long activityId) {
    return list("activity.id", activityId);
  }

  @Override
  protected String getEntityName() {
    return "experiences e";
  }
}