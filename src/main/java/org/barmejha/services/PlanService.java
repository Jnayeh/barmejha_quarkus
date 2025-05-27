package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.PlanDTO;
import org.barmejha.domain.entities.Plan;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.PlanRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@RequiredArgsConstructor
public class PlanService implements IEntityService<Plan, PlanDTO> {

  private final PlanRepository planRepository;
  private final HeaderHolder headerHolder;


  @Override
  @WithSession
  public Uni<List<PlanDTO>> getAll(HttpHeaders headers) {
    return query(headers, QueryRequest.builder().build());
  }

  @Override
  @WithSession
  public Uni<List<PlanDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    queryRequest.setJoins(new ArrayList<>(initJoins(queryRequest)));
    return planRepository.findByQuery(queryRequest).map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<PlanDTO> getById(HttpHeaders headers, Long id) {
    return planRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction
  public Uni<Response> create(HttpHeaders headers, Plan entity) {
    return planRepository.persist(entity).map(this::toDTO).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> update(HttpHeaders headers, Long id, Plan updatedEntity) {
    return planRepository.findById(id).onItem().transform(found -> {
      found.setTitle(updatedEntity.getTitle());
      found.setDescription(updatedEntity.getDescription());
      found.setStartTime(updatedEntity.getStartTime());
      found.setEndTime(updatedEntity.getEndTime());
      found.setFinalPrice(updatedEntity.getFinalPrice());
      found.setMinParticipants(updatedEntity.getMinParticipants());
      found.setMaxParticipants(updatedEntity.getMaxParticipants());
      found.setParticipants(updatedEntity.getParticipants());
      found.setStatus(updatedEntity.getStatus());
      found.setSchedule(updatedEntity.getSchedule());
      found.setPaymentType(updatedEntity.getPaymentType());
      return found;
    }).flatMap(planRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return planRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return planRepository.countByQuery(request);
  }

  @Override
  public PlanDTO toDTO(Plan entity) {
    if (entity == null) return null;
    return PlanDTO.fromEntity(entity, headerHolder.getLang());
  }

    public Set<String> initJoins(QueryRequest queryRequest) {
    HashSet<String> joins = new HashSet<>(Set.of("schedule", "client"));
    if (queryRequest.getJoins() == null) {
      return joins;
    }
    joins.addAll(queryRequest.getJoins());
    return joins;
  }
}