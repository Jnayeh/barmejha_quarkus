package org.barmejha.domain.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operator {
  EQ("="),
  NE("!="),
  GT(">"),
  LT("<"),
  GTE(">="),
  LTE("<="),
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