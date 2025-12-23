package org.barmejha.services.users;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.Admin;
import org.barmejha.domain.entities.users.Client;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.domain.entities.users.User;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.users.UserRepository;
import org.barmejha.services._interface.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class UserService implements IEntityService<User, UserDTO> {

  private final UserRepository userRepository;
  private final HeaderHolder headerHolder;

  @Override
  @WithSession
  public Uni<List<UserDTO>> getAll(HttpHeaders headers) {
    return query(headers, QueryRequest.builder().build());
  }

  @Override
  @WithSession
  public Uni<List<UserDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    queryRequest.setJoins(new ArrayList<>(initJoins(queryRequest)));
    return userRepository.findByQuery(queryRequest).map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<UserDTO> getById(HttpHeaders headers, Long id) {
    return userRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction
  public Uni<Response> create(HttpHeaders headers, User entity) {
    log.warn("Creating user: {}", entity);
    switch (entity.getType()) {
      case CLIENT -> {
        Client u = (Client) entity;
        return userRepository.persist(u).map(this::toDTO).map(ServiceUtils::createdResponse)
            .onItem().failWith(t -> new Throwable("custom transaction check"));
      }
      case ADMIN -> {
        Admin a = (Admin) entity;
        return userRepository.persist(a).map(this::toDTO).map(ServiceUtils::createdResponse);
      }
      case PROVIDER -> {
        Provider p = (Provider) entity;
        return userRepository.persist(p).map(this::toDTO).map(ServiceUtils::createdResponse);
      }
    }
    return null;
  }

  @Override
  @WithTransaction
  public Uni<Response> update(HttpHeaders headers, Long id, User updatedEntity) {
    return userRepository.findById(id).onItem().transform(found -> {
      found.setEmail(updatedEntity.getEmail());
      found.setFirstName(updatedEntity.getFirstName());
      found.setUserName(updatedEntity.getUserName());
      found.setLastName(updatedEntity.getLastName());
      return found;
    }).flatMap(userRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }


  @WithTransaction
  public Uni<Response> changePass(HttpHeaders headers, Long id, User updatedEntity) {
    return userRepository.findById(id).onItem().transform(found -> {
      found.setPasswordHash(updatedEntity.getPassword());
      return found;
    }).flatMap(userRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return userRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return userRepository.countByQuery(request);
  }

  @Override
  public UserDTO toDTO(User entity) {
    if (entity == null) return null;
    return UserDTO.fromEntity(entity, headerHolder.getLang());
  }

    public Set<String> initJoins(QueryRequest queryRequest) {
    HashSet<String> joins = new HashSet<>(Set.of());
    if (queryRequest.getJoins() == null) {
      return joins;
    }
    joins.addAll(queryRequest.getJoins());
    return joins;
  }
}