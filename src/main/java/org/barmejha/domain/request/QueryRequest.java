package org.barmejha.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequest {
  @Builder.Default
  private List<FilterCriteria> filters = new ArrayList<>();

  @Builder.Default
  private List<SortCriteria> sorts = new ArrayList<>();

  @Builder.Default
  private List<String> joins = new ArrayList<>();

  private Pagination pagination;

  // Fluent API methods
  public QueryRequest addFilter(String field, Operator operator, Object value) {
    return addFilter(FilterCriteria.of(field, operator, value));
  }

  public QueryRequest addFilter(FilterCriteria filter) {
    filters.add(filter);
    return this;
  }

  public QueryRequest addSort(String field) {
    return addSort(field, SortDirection.ASC);
  }

  public QueryRequest addSort(String field, SortDirection direction) {
    sorts.add(new SortCriteria(field, direction));
    return this;
  }

  public QueryRequest setPage(int page, int size) {
    this.pagination = Pagination.of(page, size);
    return this;
  }

  public enum SortDirection {
    ASC, DESC
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class FilterCriteria {
    public static final String OPERATOR_BLANK = "Operator ";
    private String field;
    private Operator operator;
    private Object value;
    private Object secondValue; // For BETWEEN operator

    public static FilterCriteria of(String field, Operator operator, Object value) {
      return new FilterCriteria(field, operator, value, null);
    }

    public static FilterCriteria between(String field, Object value1, Object value2) {
      return new FilterCriteria(field, Operator.BETWEEN, value1, value2);
    }

    public void validate() {
      if (operator.requiresValue() && value == null) {
        throw new IllegalArgumentException(OPERATOR_BLANK + operator + " requires a value");
      }
      if (operator.requiresTwoValues() && secondValue == null) {
        throw new IllegalArgumentException(OPERATOR_BLANK + operator + " requires two values");
      }
      if (operator.isCollectionOperator() && !(value instanceof Collection)) {
        throw new IllegalArgumentException(OPERATOR_BLANK + operator + " requires a collection value");
      }
    }
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SortCriteria {
    private String field;
    @Builder.Default
    private SortDirection direction = SortDirection.ASC;
  }

  @Value(staticConstructor = "of")
  public static class Pagination {
    int page;
    int size;
  }
}