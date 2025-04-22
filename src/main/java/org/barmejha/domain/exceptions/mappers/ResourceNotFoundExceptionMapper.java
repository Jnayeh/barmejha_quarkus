package org.barmejha.domain.exceptions.mappers;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.barmejha.domain.exceptions.ErrorBody;

import java.util.List;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
  @Context
  UriInfo uriInfo;

  @Override
  public Response toResponse(NotFoundException ex) {
    return Response
        .status(Response.Status.NOT_FOUND)
        .entity(new ErrorBody(
            Response.Status.NOT_FOUND.name(),
            ex.getMessage(),
            List.of(new ErrorBody.ErrorDetails(uriInfo.getPath(), ex.getMessage()))
        ))
        .build();
  }
}

