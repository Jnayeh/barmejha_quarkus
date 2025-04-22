package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.ActivityDTO;
import org.barmejha.domain.entities.Activity;
import org.barmejha.domain.mappers.config.MappingConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        TagMapper.class,
        UserMapper.class,
        MediaMapper.class,
        ScheduleMapper.class,
        ExperienceMapper.class,
        LocationMapper.class,
        CategoryMapper.class
    }
)
public interface ActivityMapper {
  ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

  // Entity → DTO
  ActivityDTO toDTO(Activity entity, String lang);

  // DTO → Entity
  Activity toEntity(ActivityDTO dto);

  @AfterMapping
  default void setLocalizedFields(Activity activity, ActivityDTO activityDTO, String lang) {
  }

}