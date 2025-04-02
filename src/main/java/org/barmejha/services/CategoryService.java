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
import org.barmejha.domain.entities.Category;
import org.barmejha.repositories.CategoryRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class CategoryService implements IEntityService<Category> {

  private final CategoryRepository categoryRepository;

  @Override
  @WithSession
  public Uni<List<Category>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return categoryRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<Category>> query(HttpHeaders headers, QueryRequest<Category> queryRequest) {
    return categoryRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<Category> getById(HttpHeaders headers, Long id) {
    return categoryRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, Category entity) {
    return categoryRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> update(HttpHeaders headers, Long id, Category updatedEntity) {
    return categoryRepository.findById(id).onItem().transform(found -> {
      found.setName(updatedEntity.getName());
      found.setHexColor(updatedEntity.getHexColor());
      return found;
    }).map(categoryRepository::persist).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return categoryRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<Category> request) {
    return categoryRepository.countByQuery(request);
  }
}