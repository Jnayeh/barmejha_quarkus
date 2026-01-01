package org.barmejha.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Plan;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class PlanRepository extends GenericRepository<Plan> {
  @Override
  protected String getEntityName() {
    return "Plan e";
  }
}