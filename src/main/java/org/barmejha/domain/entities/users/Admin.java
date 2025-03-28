package org.barmejha.domain.entities.users;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
  // Admin-specific fields
}
