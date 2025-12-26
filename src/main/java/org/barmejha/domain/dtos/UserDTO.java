package org.barmejha.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.users.Admin;
import org.barmejha.domain.entities.users.Client;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.domain.entities.users.User;
import org.barmejha.domain.enums.UserType;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(
    Long id,
    @Email String email,
    UserType type,
    String userName,
    String firstName,
    String lastName,
    Set<Long> preferredTagIds,
    String businessName,
    String taxId,
    String logo,
    List<ActivityDTO> activities
) {
  public static UserDTO fromEntity(User entity, List<String> joins, String lang) {
    if (entity == null) return null;
    switch (entity.getType()) {
      case CLIENT -> {
        return fromEntities((Client) entity, lang);
      }
      case PROVIDER -> {
        return fromEntities((Provider) entity, lang);
      }
      case ADMIN -> {
        return fromEntities((Admin) entity, lang);
      }
    }
    return null;
  }

  public static Set<UserDTO> mapToSetIfInitialized(Set<User> entities, List<String> joins, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }

  public static List<UserDTO> mapToListIfInitialized(List<User> entities, List<String> joins, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }

  public static UserDTO fromEntities(Client client, String lang) {
    return new UserDTO(
        client.getId(),
        client.getEmail(),
        client.getType(),
        client.getUserName(),
        client.getFirstName(),
        client.getLastName(),
        DTOUtils.mapToSetIfInitialized(client.getPreferredTagIds(), id -> id),
        null, null, null, null
    );
  }

  public static UserDTO fromEntities(Provider provider, String lang) {
    return new UserDTO(
        provider.getId(),
        provider.getEmail(),
        provider.getType(),
        provider.getUserName(),
        provider.getFirstName(),
        provider.getLastName(),
        null,
        provider.getBusinessName(),
        provider.getTaxId(),
        provider.getLogo(),
        DTOUtils.mapIfInitialized(provider.getActivities(), a -> ActivityDTO.fromEntity(a, List.of(), lang))
    );
  }

  public static UserDTO fromEntities(Admin admin, String lang) {
    return new UserDTO(
        admin.getId(),
        admin.getEmail(),
        admin.getType(),
        admin.getUserName(),
        admin.getFirstName(),
        admin.getLastName(),
        null, null, null, null, null
    );
  }
}
