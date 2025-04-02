package org.barmejha.rest.interfaces;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.services.interfaces.IEntityService;

import java.util.List;

@ApplicationScoped
public abstract class IEntityResource<T> {

  @Inject
  Instance<IEntityService<T>> abstractServiceInstance;

  @GET
  @NonBlocking
  public Uni<List<T>> getAll(
      HttpHeaders headers,
      @QueryParam("lang") String lang,
      @QueryParam("tenant_id") String tenantId) {
    return abstractServiceInstance.get().getAll(headers, lang, tenantId);
  }

  @POST
  @NonBlocking
  @Path("/query")
  public Uni<List<T>> query(HttpHeaders headers, QueryRequest<T> queryRequest) {
    return abstractServiceInstance.get().query(headers, queryRequest);
  }

  @GET
  @NonBlocking
  @Path("/{id}")
  public Uni<T> getById(HttpHeaders headers, @PathParam("id") Long id) {
    return abstractServiceInstance.get().getById(headers, id);
  }

  @POST
  @NonBlocking
  @Transactional
  public Uni<Response> create(HttpHeaders headers, T entity) {
    return abstractServiceInstance.get().create(headers, entity);
  }

  @PUT
  @NonBlocking
  @Transactional
  @Path("/{id}")
  public Uni<Response> update(HttpHeaders headers, @PathParam("id") Long id, T updatedEntity) {
    return abstractServiceInstance.get().update(headers, id, updatedEntity);
  }

  @DELETE
  @NonBlocking
  @Transactional
  @Path("/{id}")
  public Uni<Response> delete(HttpHeaders headers, @PathParam("id") Long id) {
    return abstractServiceInstance.get().delete(headers, id);
  }
}