package org.barmejha.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Location;

import java.util.List;

@ApplicationScoped
public class LocationRepository implements PanacheRepository<Location> {
  public List<Location> findByName(String name) {
    return list("name", name);
  }
}