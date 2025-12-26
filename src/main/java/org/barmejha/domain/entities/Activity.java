package org.barmejha.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.media.MediaContent;
import org.barmejha.domain.entities.users.Provider;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotNull
  private String name;

  @Column(length = 1000)
  private String description;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
  @JsonBackReference
  private Provider provider;

  @ManyToMany
  @JoinTable(name = "activity_tags")
  private Set<Tag> tags;

  @OneToMany(mappedBy = "activity")
  @JsonManagedReference
  private List<Schedule> schedules;

  @OneToMany(mappedBy = "activity")
  @JsonManagedReference
  private List<Experience> experiences;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  @JsonBackReference
  private Location location;

  @ManyToMany
  @JoinTable(name = "activity_categories")
  @JsonManagedReference
  private Set<Category> categories;
}