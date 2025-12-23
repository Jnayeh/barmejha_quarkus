package org.barmejha.repositories.users;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.users.Client;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class ClientRepository extends GenericRepository<Client> {
  public Uni<Client> findByEmail(String email) {
    return find("email", email).firstResult();
  }

  @Override
  protected String getEntityName() {
    return "users e";
  }
}