package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.LocationDTO;
import org.barmejha.domain.entities.Location;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.LocationRepository;
import org.barmejha.services._interface.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@RequiredArgsConstructor
public class LocationService implements IEntityService<Location, LocationDTO> {

  private final LocationRepository locationRepository;
  private final HeaderHolder headerHolder;

  @Override
  @WithSession
  public Uni<List<LocationDTO>> getAll(HttpHeaders headers) {
    return locationRepository.findAll().list().map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<List<LocationDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    return locationRepository.findByQuery(queryRequest).map(this::toDTO);
  }

  @Override
  @WithSession
  public Uni<LocationDTO> getById(HttpHeaders headers, Long id) {
    return locationRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction
  public Uni<Response> create(HttpHeaders headers, Location entity) {
    return locationRepository.persist(entity).map(this::toDTO).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> update(HttpHeaders headers, Long id, Location updatedEntity) {
    return locationRepository.findById(id).onItem().transform(found -> {
      found.setName(updatedEntity.getName());
      found.setAddress(updatedEntity.getAddress());
      found.setLatitude(updatedEntity.getLatitude());
      found.setLongitude(updatedEntity.getLongitude());
      return found;
    }).flatMap(locationRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return locationRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.noContent().build();
      return Response.status(404).build();
    });
  }

  @Override
  public LocationDTO toDTO(Location entity) {
    if (entity == null) return null;
    return LocationDTO.fromEntity(entity, headerHolder.getLang());
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return locationRepository.countByQuery(request);
  }

  public Set<String> initJoins(QueryRequest queryRequest) {
    HashSet<String> joins = new HashSet<>(Set.of());
    if (queryRequest.getJoins() == null) {
      return joins;
    }
    joins.addAll(queryRequest.getJoins());
    return joins;
  }
}