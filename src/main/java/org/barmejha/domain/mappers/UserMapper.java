package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.UserDTO;
import org.barmejha.domain.entities.users.User;
import org.barmejha.domain.mappers.config.MappingConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {ActivityMapper.class}
)
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  // Entity → DTO
  UserDTO toDTO(User user, String lang);

  // DTO → Entity
  User toUser(UserDTO userDTO);
}