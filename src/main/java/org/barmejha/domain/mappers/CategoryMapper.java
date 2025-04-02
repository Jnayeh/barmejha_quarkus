package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.CategoryDTO;
import org.barmejha.domain.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        ActivityMapper.class,
        MediaMapper.class
    }
)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // Entity → DTO
    CategoryDTO toDTO(Category entity);

    // DTO → Entity
    Category toEntity(CategoryDTO dto);
}