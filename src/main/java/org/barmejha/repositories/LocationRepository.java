package org.barmejha.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Location;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class LocationRepository extends GenericRepository<Location> {

  @Override
  protected String getEntityName() {
    return "Location e";
  }
}