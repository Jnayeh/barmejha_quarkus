package org.barmejha.domain.dtos.utils;

import org.hibernate.Hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class DTOUtils {
  private DTOUtils() {
  }

  public static <T, R> List<R> mapIfInitialized(Collection<T> collection, Function<T, R> mapper) {
    if (collection == null || !Hibernate.isInitialized(collection)) {
      return List.of();
    }
    return collection.stream().map(mapper).toList();
  }

  public static <T, R> Set<R> mapToSetIfInitialized(Collection<T> collection, Function<T, R> mapper) {
    if (collection == null || !Hibernate.isInitialized(collection)) {
      return Set.of();
    }
    return collection.stream().map(mapper).collect(Collectors.toSet());
  }
}