package org.barmejha.rest.abstraction;

import io.quarkus.logging.Log;
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
import java.util.Objects;

import static org.barmejha.services.utils.ServiceUtils.okResponse;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntityResource<T, D> {

  protected IEntityService<T, D> iEntityService;

  @GET
  @NonBlocking
  public Uni<List<D>> getAll(
      @Context HttpHeaders headers) {
    long startTime = System.currentTimeMillis();
    return iEntityService.getAll(headers).invoke(res -> printResponseLog(okResponse(res), startTime));
  }

  @POST
  @NonBlocking
  @Path("/query")
  public Uni<List<D>> query(@Context HttpHeaders headers, QueryRequest queryRequest) {
    long startTime = System.currentTimeMillis();
    return iEntityService.query(headers, queryRequest).invoke(res -> printResponseLog(okResponse(res), startTime));
  }

  @GET
  @NonBlocking
  @Path("/{id}")
  public Uni<D> getById(@Context HttpHeaders headers, @PathParam("id") Long id) {
    long startTime = System.currentTimeMillis();
    return iEntityService.getById(headers, id).invoke(res -> printResponseLog(okResponse(res), startTime));
  }

  @POST
  @NonBlocking
  public Uni<Response> create(@Context HttpHeaders headers, T entity) {
    long startTime = System.currentTimeMillis();
    return iEntityService.create(headers, entity).invoke(res -> printResponseLog(res, startTime));
  }

  @PUT
  @NonBlocking
  @Path("/{id}")
  public Uni<Response> update(@Context HttpHeaders headers, @PathParam("id") Long id, T updatedEntity) {
    long startTime = System.currentTimeMillis();
    return iEntityService.update(headers, id, updatedEntity).invoke(res -> printResponseLog(res, startTime));
  }

  @DELETE
  @NonBlocking
  @Path("/{id}")
  public Uni<Response> delete(@Context HttpHeaders headers, @PathParam("id") Long id) {
    long startTime = System.currentTimeMillis();
    return iEntityService.delete(headers, id).invoke(res -> printResponseLog(res, startTime));
  }


  private void printResponseLog(Response res, long startTime) {
    Log.warnf("response ==> %s", Objects.toString(res.getEntity(), "null"));
    Log.warnf("Successfully returned in %d ms", System.currentTimeMillis() - startTime);
  }
}