package org.barmejha.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBody {
  private String code;
  private String message;
  private List<ErrorDetails> details;

  public ErrorBody(String message) {
    this.message = message;
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ErrorDetails {
    private String field;
    private String message;
  }
}
