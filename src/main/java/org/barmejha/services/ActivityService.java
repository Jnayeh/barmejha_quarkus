package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.domain.entities.Activity;
import org.barmejha.domain.exceptions.ErrorBody;
import org.barmejha.repositories.ActivityRepository;
import org.barmejha.repositories.LocationRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

import static org.barmejha.domain.exceptions.ResException.internalServerFailure;

@ApplicationScoped
@RequiredArgsConstructor
public class ActivityService implements IEntityService<Activity> {

  private final ActivityRepository activityRepository;
  private final LocationRepository locationRepository;

  @Override
  @WithSession
  public Uni<List<Activity>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return activityRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<Activity>> query(HttpHeaders headers, QueryRequest<Activity> queryRequest) {
    return activityRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<Activity> getById(HttpHeaders headers, Long id) {
    return activityRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, Activity activity) {
    return locationRepository.persist(activity.getLocation())
        .flatMap(location -> activityRepository.persist(activity))
        .onItem().transform(ServiceUtils::createdResponse)
        .onFailure().transform(WebApplicationException::new);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> update(HttpHeaders headers, Long id, Activity updatedEntity) {
    return activityRepository.findById(id)
        .map(found -> {
          if (found == null) {
            return ServiceUtils.notFoundResponse(new ErrorBody("Entity does not exist"));
          }
          found.setName(updatedEntity.getName());
          found.setDescription(updatedEntity.getDescription());
          found.setDuration(updatedEntity.getDuration());
          found.setBasePrice(updatedEntity.getBasePrice());
          found.setDiscountPrice(updatedEntity.getDiscountPrice());
          return ServiceUtils.okResponse(found);
        })
        .onFailure()
        .recoverWithItem(t -> internalServerFailure(t, new ErrorBody("Internal server error")));
  }

  @Override
  @WithSession
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return activityRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<Activity> request) {
    return activityRepository.countByQuery(request);
  }
}