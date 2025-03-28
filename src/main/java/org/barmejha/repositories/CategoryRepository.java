package org.barmejha.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.Category;

import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
  public Optional<Category> findByName(String name) {
    return find("name", name).firstResultOptional();
  }
}