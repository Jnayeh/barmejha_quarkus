package org.barmejha.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.media.MediaContent;
import org.barmejha.domain.entities.users.Provider;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "activities")
@Valid
public class Activity extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(nullable = false)
  @NotNull
  public String name;

  @Column(length = 1000)
  public String description;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "activity_id")
  public List<MediaContent> media;

  @Column(nullable = false)
  @NotNull
  public Long duration;

  @Column(nullable = false)
  @NotNull
  public BigDecimal basePrice;

  public BigDecimal discountPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  public Provider provider;

  @ManyToMany
  @JoinTable(name = "activity_tags")
  public Set<Tag> tags;

  @OneToMany(mappedBy = "activity")
  public List<Schedule> schedules;

  @OneToMany(mappedBy = "activity")
  public List<Experience> experiences;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  public Location location;

  @ManyToMany
  @JoinTable(name = "activity_categories")
  public Set<Category> categories;
}