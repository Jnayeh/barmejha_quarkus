package org.barmejha.services.interfaces;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.User;
import org.barmejha.domain.request.QueryRequest;

import java.util.ArrayList;
import java.util.List;

public interface IEntityService<E, D> {

  Uni<List<D>> getAll(HttpHeaders headers);

  Uni<List<D>> query(HttpHeaders headers, QueryRequest queryRequest);

  Uni<D> getById(HttpHeaders headers, Long id);

  Uni<Response> create(HttpHeaders headers, E entity);


  Uni<Response> update(HttpHeaders headers, Long id, E updatedEntity);

  Uni<Response> delete(HttpHeaders headers, Long id);

  Uni<Long> count(HttpHeaders headers, QueryRequest request);

  D toDTO(E entity);

  default List<D> toDTO(List<E> entityList) {
    if (entityList == null) return new ArrayList<>();
    return entityList.stream().map(this::toDTO).toList();
  }
}