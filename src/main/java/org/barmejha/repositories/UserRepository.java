package org.barmejha.repositories;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.users.User;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class UserRepository extends GenericRepository<User> {
  public Uni<User> findByEmail(String email) {
    return find("email", email).firstResult();
  }

  @Override
  protected String getEntityName() {
    return "users e";
  }
}