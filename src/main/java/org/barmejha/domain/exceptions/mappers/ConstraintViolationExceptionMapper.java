package org.barmejha.domain.exceptions.mappers;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.barmejha.domain.exceptions.ErrorBody;
import org.barmejha.domain.exceptions.enums.ErrorCode;

import java.util.List;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
  @Context
  UriInfo uriInfo;

  @Override
  public Response toResponse(ConstraintViolationException ex) {
    List<ErrorBody.ErrorDetails> errors = ex.getConstraintViolations()
        .stream()
        .map(v -> new ErrorBody.ErrorDetails(
            v.getPropertyPath().toString(),
            v.getMessage()
        )).toList();

    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity(new ErrorBody(
            Response.Status.BAD_REQUEST.name(),
            ErrorCode.INVALID_REQUEST.getMessage(),
            errors
        ))
        .build();
  }
}
