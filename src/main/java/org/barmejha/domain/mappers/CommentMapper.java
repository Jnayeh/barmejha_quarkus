package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.CommentDTO;
import org.barmejha.domain.entities.communities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        PostMapper.class,
        PlanMapper.class,
        UserMapper.class
    }
)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // Entity → DTO
    CommentDTO toDTO(Comment entity);

    // DTO → Entity
    Comment toEntity(CommentDTO dto);
}