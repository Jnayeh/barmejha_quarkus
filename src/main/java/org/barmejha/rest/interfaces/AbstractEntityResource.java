package org.barmejha.rest.interfaces;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.services.interfaces.IEntityService;

import java.util.List;

@ApplicationScoped
public abstract class AbstractEntityResource<T, D> {

  @Inject
  Instance<IEntityService<T, D>> abstractServiceInstance;

  @GET
  @NonBlocking
  public Uni<List<D>> getAll(
      @Context HttpHeaders headers, QueryRequest queryRequest) {
    return abstractServiceInstance.get().getAll(headers, queryRequest);
  }

  @POST
  @NonBlocking
  @Path("/query")
  public Uni<List<D>> query(@Context HttpHeaders headers, QueryRequest queryRequest) {
    return abstractServiceInstance.get().query(headers, queryRequest);
  }

  @GET
  @NonBlocking
  @Path("/{id}")
  public Uni<D> getById(@Context HttpHeaders headers, @PathParam("id") Long id) {
    return abstractServiceInstance.get().getById(headers, id);
  }

  @POST
  @NonBlocking
  public Uni<Response> create(@Context HttpHeaders headers, T entity) {
    return abstractServiceInstance.get().create(headers, entity);
  }

  @PUT
  @NonBlocking
  @Path("/{id}")
  public Uni<Response> update(@Context HttpHeaders headers, @PathParam("id") Long id, T updatedEntity) {
    return abstractServiceInstance.get().update(headers, id, updatedEntity);
  }

  @DELETE
  @NonBlocking
  @Path("/{id}")
  public Uni<Response> delete(@Context HttpHeaders headers, @PathParam("id") Long id) {
    return abstractServiceInstance.get().delete(headers, id);
  }
}