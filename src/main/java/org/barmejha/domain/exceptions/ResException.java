package org.barmejha.domain.exceptions;

import io.quarkus.logging.Log;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.barmejha.services.utils.ServiceUtils;

public class ResException extends WebApplicationException {
  private static final long serialVersionUID = 1L;

  public static Response internalServerFailure(Throwable t, Object error) {
    Log.error(t.getMessage(), t);
    return ServiceUtils.internalServerErrorResponse(error);
  }
}