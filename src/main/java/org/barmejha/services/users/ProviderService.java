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
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.domain.entities.users.User;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.users.ProviderRepository;
import org.barmejha.services._interface.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ProviderService implements IEntityService<Provider, UserDTO> {

  private final ProviderRepository providerRepository;
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
    return providerRepository.findByQuery(queryRequest).map(this::toDTO);
  }


  @Override
  @WithSession
  public Uni<UserDTO> getById(HttpHeaders headers, Long id) {
    return providerRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction
  public Uni<Response> create(HttpHeaders headers, Provider entity) {
    log.warn("Creating user: {}", entity);

    return providerRepository.persist(entity).map(this::toDTO).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> update(HttpHeaders headers, Long id, Provider updatedEntity) {
    return providerRepository.findById(id).onItem().transform(found -> {
      found.setEmail(updatedEntity.getEmail());
      found.setFirstName(updatedEntity.getFirstName());
      found.setUserName(updatedEntity.getUserName());
      found.setLastName(updatedEntity.getLastName());
      return found;
    }).flatMap(providerRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }


  @WithTransaction
  public Uni<Response> changePass(HttpHeaders headers, Long id, Provider updatedEntity) {
    return providerRepository.findById(id).onItem().transform(found -> {
      found.setPasswordHash(updatedEntity.getPassword());
      return found;
    }).flatMap(providerRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return providerRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return providerRepository.countByQuery(request);
  }

  @Override
  public UserDTO toDTO(Provider entity) {
    if (entity == null) return null;
    return UserDTO.fromEntity(entity, headerHolder.getLang());
  }

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