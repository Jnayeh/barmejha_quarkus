package org.barmejha.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Activity;

import java.util.List;

@ApplicationScoped
public class ActivityRepository implements PanacheRepository<Activity> {

  public List<Activity> findByProviderId(Long providerId) {
    return list("provider.id", providerId);
  }
}