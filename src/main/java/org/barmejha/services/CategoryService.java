package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.CategoryDTO;
import org.barmejha.domain.entities.Category;
import org.barmejha.domain.mappers.CategoryMapper;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.CategoryRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class CategoryService implements IEntityService<Category, CategoryDTO> {

  private final CategoryRepository categoryRepository;
  private final HeaderHolder headerHolder;

  @Override
  @WithSession
  public Uni<List<CategoryDTO>> getAll(HttpHeaders headers) {
    return categoryRepository.listAll().map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<List<CategoryDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    return categoryRepository.findByQuery(queryRequest).map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<CategoryDTO> getById(HttpHeaders headers, Long id) {
    return categoryRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction
  public Uni<Response> create(HttpHeaders headers, Category entity) {
    return categoryRepository.persist(entity).map(this::toDTO).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> update(HttpHeaders headers, Long id, Category updatedEntity) {
    return categoryRepository.findById(id).onItem().transform(found -> {
      found.setName(updatedEntity.getName());
      found.setHexColor(updatedEntity.getHexColor());
      return found;
    }).flatMap(categoryRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return categoryRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return categoryRepository.countByQuery(request);
  }

  @Override
  public CategoryDTO toDTO(Category entity) {
    if (entity == null) return null;
    return CategoryMapper.INSTANCE.toDTO(entity, headerHolder.getLang());
  }
}