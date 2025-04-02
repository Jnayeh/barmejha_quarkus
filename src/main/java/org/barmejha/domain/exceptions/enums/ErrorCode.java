package org.barmejha.domain.exceptions.enums;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public enum ErrorCode {
  INVALID_REQUEST("Invalid request format (JSON request could not be parsed)."),
  NOT_FOUND_RESOURCE("Not Found Resource !");

  private final String message;

  ErrorCode(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return Response.Status.BAD_REQUEST + ": " + this.message;
  }
}