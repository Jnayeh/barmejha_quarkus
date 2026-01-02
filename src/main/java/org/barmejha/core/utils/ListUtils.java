package org.barmejha.core.utils;

import java.util.List;

public class ListUtils {
  private ListUtils() {}

  public static <T> boolean isNotEmpty(List<T> list) {
    return ! isEmpty(list);
  }
  public static <T> boolean isEmpty(List<T> list) {
    return list == null || list.isEmpty();
  }
}
