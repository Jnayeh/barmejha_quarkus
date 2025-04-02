package org.barmejha.services.utils;

import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceUtils {
  public static Response okResponse(Object object) {
    return Response.ok(object).build();
  }

  public static Response createdResponse(Object object) {
    return Response.status(Response.Status.CREATED).entity(object).build();
  }

  public static Response badRequestResponse(Object error) {
    return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
  }

  public static Response notFoundResponse(Object error) {
    return Response.status(Response.Status.NOT_FOUND).entity(error).build();
  }

  public static Response internalServerErrorResponse(Object error) {
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
  }
}
