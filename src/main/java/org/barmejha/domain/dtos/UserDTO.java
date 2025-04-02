package org.barmejha.domain.dtos;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.barmejha.domain.entities.Activity;
import org.barmejha.domain.enums.UserType;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;

  @Email
  private String email;

  private String passwordHash;

  private UserType type;

  private String userName;

  private String firstName;

  private String lastName;

  private Set<Long> preferredTagIds;

  private String businessName;

  private String taxId;

  private String logo;

  private List<Activity> activities;
}
