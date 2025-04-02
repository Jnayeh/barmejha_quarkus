package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.PostDTO;
import org.barmejha.domain.entities.communities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        CommunityMapper.class,
        CommentMapper.class,
        UserMapper.class
    }
)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    // Entity → DTO
    PostDTO toDTO(Post entity);

    // DTO → Entity
    Post toEntity(PostDTO dto);
}