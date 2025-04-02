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
import org.barmejha.domain.entities.Schedule;
import org.barmejha.repositories.ScheduleRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ScheduleService implements IEntityService<Schedule> {

  private final ScheduleRepository scheduleRepository;

  public Uni<List<Schedule>> getAllSchedulesByActivity(Long id) {
    return scheduleRepository.findByActivityId(id);
  }

  @Override
  @WithSession
  public Uni<List<Schedule>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return scheduleRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<Schedule>> query(HttpHeaders headers, QueryRequest<Schedule> queryRequest) {
    return scheduleRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<Schedule> getById(HttpHeaders headers, Long id) {
    return scheduleRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, Schedule entity) {
    return scheduleRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> update(HttpHeaders headers, Long id, Schedule updatedEntity) {
    return scheduleRepository.findById(id).onItem().transform(found -> {
      found.setStartDate(updatedEntity.getStartDate());
      found.setEndDate(updatedEntity.getEndDate());
      found.setRecurrence(updatedEntity.getRecurrence());
      found.setRecurringDays(updatedEntity.getRecurringDays());
      found.setStartTime(updatedEntity.getStartTime());
      found.setEndTime(updatedEntity.getEndTime());
      found.setBreaks(updatedEntity.getBreaks());
      found.setExcludedDates(updatedEntity.getExcludedDates());
      return found;
    }).map(scheduleRepository::persist).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return scheduleRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<Schedule> request) {
    return scheduleRepository.countByQuery(request);
  }
}