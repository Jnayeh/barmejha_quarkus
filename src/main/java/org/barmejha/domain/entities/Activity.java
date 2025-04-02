package org.barmejha.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.media.MediaContent;
import org.barmejha.domain.entities.users.Provider;
import org.barmejha.domain.id.USID;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Valid
@Entity(name = "activities")
public class Activity extends AuditedEntity {

  @Id
  @USID
  private Long id;

  @Column(nullable = false)
  @NotNull
  private String name;

  @Column(length = 1000)
  private String description;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "activity_id")
  private List<MediaContent> media;

  @Column(nullable = false)
  @NotNull
  private int duration;

  @Column(name = "base_price", nullable = false)
  @NotNull
  private BigDecimal basePrice;

  @Column(name = "discount_price")
  private BigDecimal discountPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  private Provider provider;

  @ManyToMany
  @JoinTable(name = "activity_tags")
  private Set<Tag> tags;

  @OneToMany(mappedBy = "activity")
  @JsonIgnoreProperties("activity")
  private List<Schedule> schedules;

  @OneToMany(mappedBy = "activity")
  @JsonIgnoreProperties("activity")
  private List<Experience> experiences;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @ManyToMany
  @JoinTable(name = "activity_categories")
  private Set<Category> categories;
}