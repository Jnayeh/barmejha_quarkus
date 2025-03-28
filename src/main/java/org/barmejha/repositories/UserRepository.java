package org.barmejha.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.users.User;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
  public Optional<User> findByEmail(String email) {
    return find("email", email).firstResultOptional();
  }
}