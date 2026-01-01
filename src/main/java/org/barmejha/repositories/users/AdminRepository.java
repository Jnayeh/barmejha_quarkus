package org.barmejha.repositories.users;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.users.Admin;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class AdminRepository extends GenericRepository<Admin> {
  public Uni<Admin> findByEmail(String email) {
    return find("email", email).firstResult();
  }

  @Override
  protected String getEntityName() {
    return "Admin e";
  }
}