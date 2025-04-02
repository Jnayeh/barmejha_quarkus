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
import org.barmejha.domain.entities.Experience;
import org.barmejha.repositories.ExperienceRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ExperienceService implements IEntityService<Experience> {

  private final ExperienceRepository experienceRepository;

  @Override
  @WithSession
  public Uni<List<Experience>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return experienceRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<Experience>> query(HttpHeaders headers, QueryRequest<Experience> queryRequest) {
    return experienceRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<Experience> getById(HttpHeaders headers, Long id) {
    return experienceRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, Experience entity) {
    return experienceRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> update(HttpHeaders headers, Long id, Experience updatedEntity) {
    return experienceRepository.findById(id).onItem().transform(found -> {
      found.setMedia(updatedEntity.getMedia());
      found.setRating(updatedEntity.getRating());
      found.setReview(updatedEntity.getReview());
      return found;
    }).map(experienceRepository::persist).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return experienceRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<Experience> request) {
    return experienceRepository.countByQuery(request);
  }
}