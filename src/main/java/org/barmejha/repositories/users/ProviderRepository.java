package org.barmejha.repositories.users;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class ProviderRepository extends GenericRepository<Provider> {
  public Uni<Provider> findByEmail(String email) {
    return find("email", email).firstResult();
  }

  @Override
  protected String getEntityName() {
    return "users e";
  }
}