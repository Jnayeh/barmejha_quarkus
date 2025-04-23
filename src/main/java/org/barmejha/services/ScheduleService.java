package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.ScheduleDTO;
import org.barmejha.domain.entities.Schedule;
import org.barmejha.domain.mappers.ScheduleMapper;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.ScheduleRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ScheduleService implements IEntityService<Schedule, ScheduleDTO> {

  private final ScheduleRepository scheduleRepository;
  private final HeaderHolder headerHolder;

  public Uni<List<ScheduleDTO>> getAllSchedulesByActivity(Long id) {
    return scheduleRepository.findByActivityId(id).map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<List<ScheduleDTO>> getAll(HttpHeaders headers) {
    return scheduleRepository.listAll().map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<List<ScheduleDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    return scheduleRepository.findByQuery(queryRequest).map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<ScheduleDTO> getById(HttpHeaders headers, Long id) {
    return scheduleRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction 
  public Uni<Response> create(HttpHeaders headers, Schedule entity) {
    return scheduleRepository.persist(entity).map(this::toDTO).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction 
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
    }).flatMap(scheduleRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction 
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return scheduleRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return scheduleRepository.countByQuery(request);
  }

  @Override
  public ScheduleDTO toDTO(Schedule entity) {
    if (entity == null) return null;
    return ScheduleMapper.INSTANCE.toDTO(entity, headerHolder.getLang());
  }
}