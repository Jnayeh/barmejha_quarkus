package org.barmejha.domain.entities.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.barmejha.domain.entities.Activity;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("PROVIDER")
public class Provider extends User {

  @Column(nullable = false)
  public String businessName;

  @Column(unique = true)
  public String taxId;

  public String logo;

  @OneToMany(mappedBy = "provider")
  public List<Activity> activities;
}
