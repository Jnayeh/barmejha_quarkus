package org.barmejha.domain.entities.users;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

  @ElementCollection
  @CollectionTable(name = "client_preferences")
  private Set<Long> preferredTagIds;
}
