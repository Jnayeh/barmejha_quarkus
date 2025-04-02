package org.barmejha.repositories;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Activity;
import org.barmejha.repositories.generic.GenericRepository;

import java.util.List;

@ApplicationScoped
public class ActivityRepository extends GenericRepository<Activity> {

  public Uni<List<Activity>> findByProviderId(Long providerId) {
    return list("provider.id", providerId);
  }

  @Override
  protected String getEntityName() {
    return "activities e";
  }
}