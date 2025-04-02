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
import org.barmejha.domain.entities.Plan;
import org.barmejha.repositories.PlanRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class PlanService implements IEntityService<Plan> {

  private final PlanRepository planRepository;


  @Override
  @WithSession
  public Uni<List<Plan>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return planRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<Plan>> query(HttpHeaders headers, QueryRequest<Plan> queryRequest) {
    return planRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<Plan> getById(HttpHeaders headers, Long id) {
    return planRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, Plan entity) {
    return planRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  @Transactional
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
    }).map(planRepository::persist).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return planRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<Plan> request) {
    return planRepository.countByQuery(request);
  }
}