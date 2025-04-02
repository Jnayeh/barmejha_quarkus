package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.ActivityDTO;
import org.barmejha.domain.entities.Activity;
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
    ActivityDTO toDTO(Activity entity);

    // DTO → Entity
    Activity toEntity(ActivityDTO dto);
}