package org.barmejha.domain.entities.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
  private String businessName;

  @Column(unique = true)
  private String taxId;

  private String logo;

  @OneToMany(mappedBy = "provider")
  private List<Activity> activities;
}
