package org.barmejha.domain.dtos;

import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Location;

import java.util.List;
import java.util.Set;

public record LocationDTO(
    Long id,
    String name,
    String address,
    Double latitude,
    Double longitude,
    List<ActivityDTO> activities
) {
  public static LocationDTO fromEntity(Location entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return new LocationDTO(
        entity.getId(),
        entity.getName(),
        entity.getAddress(),
        entity.getLatitude(),
        entity.getLongitude(),
        DTOUtils.mapIfInitialized(entity.getActivities(), a -> ActivityDTO.fromEntity(a, joins, lang))
    );
  }

  public static Set<LocationDTO> mapToSetIfInitialized(Set<Location> entities, List<String> joins, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }

  public static List<LocationDTO> mapToListIfInitialized(List<Location> entities, List<String> joins, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }
}