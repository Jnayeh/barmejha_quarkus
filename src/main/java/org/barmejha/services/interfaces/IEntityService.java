package org.barmejha.services.interfaces;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.barmejha.domain.request.QueryRequest;

import java.util.List;

public interface IEntityService<T> {

  Uni<List<T>> getAll(HttpHeaders headers, String lang, String tenantId);

  Uni<List<T>> query(HttpHeaders headers, QueryRequest<T> queryRequest);

  Uni<T> getById(HttpHeaders headers, Long id);

  Uni<Response> create(HttpHeaders headers, T entity);


  Uni<Response> update(HttpHeaders headers, Long id, T updatedEntity);

  Uni<Response> delete(HttpHeaders headers, Long id);

  Uni<Long> count(HttpHeaders headers, QueryRequest<T> request);
}