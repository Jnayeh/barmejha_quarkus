package org.barmejha.repositories.generic;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.barmejha.domain.request.QueryRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class GenericRepository<T> implements PanacheRepository<T> {

  public static final String SPACE = " ";

  public Uni<List<T>> findByQuery(QueryRequest queryRequest) {
    var queryParts = buildQuery(queryRequest);
    log.info("Query: {}", queryParts.query);
    var query = find(queryParts.query, queryParts.params);

    if (queryRequest.getPagination() != null) {
      var pagination = queryRequest.getPagination();
      return query.page(pagination.getPage(), pagination.getSize()).list();
    }
    return query.list();
  }

  public Uni<Long> countByQuery(QueryRequest queryRequest) {
    var queryParts = buildQuery(queryRequest);
    return count(queryParts.query, queryParts.params);
  }

  private QueryParts buildQuery(QueryRequest queryRequest) {
    var queryBuilder = new StringBuilder("FROM ").append(getEntityName());
    var params = new HashMap<String, Object>();
    var whereClauses = new ArrayList<String>();

    // Process fetch joins
    for (var joinPath : queryRequest.getJoins()) {
      queryBuilder.append(" ").append(buildFetchJoinQuery(joinPath));
    }

    // Process filters
    for (var filter : queryRequest.getFilters()) {
      filter.validate();
      whereClauses.add(buildWhereClause(filter, params));
    }

    // Add WHERE clause if needed
    if (!whereClauses.isEmpty()) {
      queryBuilder.append(" WHERE ").append(String.join(" AND ", whereClauses));
    }

    // Process sorting
    if (queryRequest.getSorts() != null && !queryRequest.getSorts().isEmpty()) {
      queryBuilder.append(" ORDER BY ")
          .append(queryRequest.getSorts().stream()
              .map(this::buildOrderByClause)
              .collect(Collectors.joining(", ")));
    }

    return new QueryParts(queryBuilder.toString(), params);
  }


  protected String buildFetchJoinQuery(String... joinPaths) {
    return Arrays.stream(joinPaths)
        .map(path -> "LEFT JOIN FETCH e." + path)
        .collect(Collectors.joining(" "));
  }

  private String buildWhereClause(QueryRequest.FilterCriteria filter, Map<String, Object> params) {
    var paramName = filter.getField().replace('.', '_');
    var fieldPath = buildFieldPath(filter.getField());

    switch (filter.getOperator()) {
      case IS_NULL:
        return fieldPath + " IS NULL";
      case IS_NOT_NULL:
        return fieldPath + " IS NOT NULL";
      case BETWEEN:
        params.put(paramName + "_1", filter.getValue());
        params.put(paramName + "_2", filter.getSecondValue());
        return fieldPath + " BETWEEN :" + paramName + "_1 AND :" + paramName + "_2";
      case IN, NOT_IN:
        params.put(paramName, filter.getValue());
        return fieldPath + SPACE + filter.getOperator().getSql() + " (:" + paramName + ")";
      case LIKE, ILIKE:
        params.put(paramName, "%" + filter.getValue() + "%");
        return fieldPath + SPACE + filter.getOperator().getSql() + " :" + paramName;
      default:
        params.put(paramName, filter.getValue());
        return fieldPath + SPACE + filter.getOperator().getSql() + " :" + paramName;
    }
  }

  private String buildOrderByClause(QueryRequest.SortCriteria sort) {
    return buildFieldPath(sort.getField()) + SPACE + sort.getDirection().name();
  }

  private String buildFieldPath(String field) {
    return field.contains(".") ? field : "e." + field;
  }

  protected abstract String getEntityName();

  private record QueryParts(String query, Map<String, Object> params) {
  }
}