package org.barmejha.repositories;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Category;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class CategoryRepository extends GenericRepository<Category> {
  public Uni<Category> findByName(String name) {
    return find("name", name).firstResult();
  }

  @Override
  protected String getEntityName() {
    return "categories e";
  }
}