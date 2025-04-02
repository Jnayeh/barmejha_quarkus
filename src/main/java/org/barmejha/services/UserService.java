package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.domain.entities.users.User;
import org.barmejha.domain.enums.UserType;
import org.barmejha.repositories.UserRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class UserService implements IEntityService<UserDTO> {

  private final UserRepository userRepository;

  @Override
  @WithSession
  public Uni<List<UserDTO>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return userRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<UserDTO>> query(HttpHeaders headers, QueryRequest<UserDTO> queryRequest) {
    return userRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<UserDTO> getById(HttpHeaders headers, Long id) {
    return userRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, UserDTO entity) {

      if (entity.getType() == UserType.PROVIDER) {
        Provider p = (Provider) entity;
      }
    return userRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> update(HttpHeaders headers, Long id, UserDTO updatedEntity) {
    return userRepository.findById(id).onItem().transform(found -> {
      found.setEmail(updatedEntity.getEmail());
      found.setFirstName(updatedEntity.getFirstName());
      found.setUserName(updatedEntity.getUserName());
      found.setLastName(updatedEntity.getLastName());
      return found;
    }).map(userRepository::persist).map(ServiceUtils::okResponse);
  }

  @Transactional
  @WithTransaction
  public Uni<Response> changePass(HttpHeaders headers, Long id, UserDTO updatedEntity) {
    return userRepository.findById(id).onItem().transform(found -> {
      found.setPasswordHash(updatedEntity.getPasswordHash());
      return found;
    }).map(userRepository::persist).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return userRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<UserDTO> request) {
    User user =
    return userRepository.countByQuery(request);
  }
}