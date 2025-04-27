package org.barmejha.rest.interfaces;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.services.interfaces.IEntityService;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntityResource<T, D> {

  protected IEntityService<T, D> iEntityService;

  @GET
  @NonBlocking
  public Uni<List<D>> getAll(
      @Context HttpHeaders headers) {
    return iEntityService.getAll(headers);
  }

  @POST
  @NonBlocking
  @Path("/query")
  public Uni<List<D>> query(@Context HttpHeaders headers, QueryRequest queryRequest) {
    return iEntityService.query(headers, queryRequest);
  }

  @GET
  @NonBlocking
  @Path("/{id}")
  public Uni<D> getById(@Context HttpHeaders headers, @PathParam("id") Long id) {
    return iEntityService.getById(headers, id);
  }

  @POST
  @NonBlocking
  public Uni<Response> create(@Context HttpHeaders headers, T entity) {
    return iEntityService.create(headers, entity);
  }

  @PUT
  @NonBlocking
  @Path("/{id}")
  public Uni<Response> update(@Context HttpHeaders headers, @PathParam("id") Long id, T updatedEntity) {
    return iEntityService.update(headers, id, updatedEntity);
  }

  @DELETE
  @NonBlocking
  @Path("/{id}")
  public Uni<Response> delete(@Context HttpHeaders headers, @PathParam("id") Long id) {
    return iEntityService.delete(headers, id);
  }
}