package org.barmejha.domain.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

  @Column(name = "business_name")
  private String businessName;

  @Column(name = "tax_id")
  private String taxId;

  private String logo;

  @OneToMany(mappedBy = "provider")
  @JsonIgnoreProperties("provider")
  private List<Activity> activities;
}
