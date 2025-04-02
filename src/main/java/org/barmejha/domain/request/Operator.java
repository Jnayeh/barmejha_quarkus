package org.barmejha.domain.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operator {
  EQUAL("="),
  NOT_EQUAL("!="),
  GREATER_THAN(">"),
  LESS_THAN("<"),
  GREATER_THAN_OR_EQUAL(">="),
  LESS_THAN_OR_EQUAL("<="),
  LIKE("LIKE"),
  ILIKE("ILIKE"),
  IN("IN"),
  NOT_IN("NOT IN"),
  IS_NULL("IS NULL"),
  IS_NOT_NULL("IS NOT NULL"),
  BETWEEN("BETWEEN");

  private final String sql;

  public boolean requiresValue() {
    return this != IS_NULL && this != IS_NOT_NULL;
  }

  public boolean requiresTwoValues() {
    return this == BETWEEN;
  }

  public boolean isCollectionOperator() {
    return this == IN || this == NOT_IN;
  }
}