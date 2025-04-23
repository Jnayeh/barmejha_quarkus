package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.ExperienceDTO;
import org.barmejha.domain.entities.Experience;
import org.barmejha.domain.mappers.ExperienceMapper;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.ExperienceRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ExperienceService implements IEntityService<Experience, ExperienceDTO> {

  private final ExperienceRepository experienceRepository;
  private final HeaderHolder headerHolder;

  @Override
  @WithSession
  public Uni<List<ExperienceDTO>> getAll(HttpHeaders headers) {
    return experienceRepository.listAll().map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<List<ExperienceDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    return experienceRepository.findByQuery(queryRequest).map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<ExperienceDTO> getById(HttpHeaders headers, Long id) {
    return experienceRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction 
  public Uni<Response> create(HttpHeaders headers, Experience entity) {
    return experienceRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction 
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
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return experienceRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return experienceRepository.countByQuery(request);
  }

  @Override
  public ExperienceDTO toDTO(Experience entity) {
    if (entity == null) return null;
    return ExperienceMapper.INSTANCE.toDTO(entity, headerHolder.getLang());
  }
}