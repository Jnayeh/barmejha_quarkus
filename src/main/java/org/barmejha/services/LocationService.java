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
import org.barmejha.domain.entities.Location;
import org.barmejha.repositories.LocationRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class LocationService implements IEntityService<Location> {

  private final LocationRepository locationRepository;

  @Override
  @WithSession
  public Uni<List<Location>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return locationRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<Location>> query(HttpHeaders headers, QueryRequest<Location> queryRequest) {
    return locationRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<Location> getById(HttpHeaders headers, Long id) {
    return locationRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, Location entity) {
    return locationRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> update(HttpHeaders headers, Long id, Location updatedEntity) {
    return locationRepository.findById(id).onItem().transform(found -> {
      found.setName(updatedEntity.getName());
      found.setAddress(updatedEntity.getAddress());
      found.setLatitude(updatedEntity.getLatitude());
      found.setLongitude(updatedEntity.getLongitude());
      return found;
    }).map(locationRepository::persist).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return locationRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.noContent().build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<Location> request) {
    return locationRepository.countByQuery(request);
  }
}