package org.barmejha.services._interface;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.barmejha.domain.request.QueryRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IEntityService<E, D> {

  Uni<List<D>> getAll(HttpHeaders headers);

  Uni<List<D>> query(HttpHeaders headers, QueryRequest queryRequest);

  Uni<D> getById(HttpHeaders headers, Long id);

  Uni<Response> create(HttpHeaders headers, E entity);


  Uni<Response> update(HttpHeaders headers, Long id, E updatedEntity);

  Uni<Response> delete(HttpHeaders headers, Long id);

  Uni<Long> count(HttpHeaders headers, QueryRequest request);

  D toDTO(E entity);
  D toDTO(E entity, List<String> joins);

  default List<D> toDTO(List<E> entityList, List<String> joins) {
    if (entityList == null) return new ArrayList<>();
    return entityList.stream().map(e -> toDTO(e, joins)).toList();
  }
  default Set<String> initJoins(QueryRequest queryRequest) {
    HashSet<String> joins = new HashSet<>(Set.of());
    if (queryRequest.getJoins() == null) {
      return joins;
    }
    joins.addAll(queryRequest.getJoins());
    return joins;
  }
}