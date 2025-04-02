package org.barmejha.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.media.MediaContent;
import org.barmejha.domain.id.USID;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity
@Table(name = "categories")
public class Category extends AuditedEntity {

  @Id
  @USID
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "category_id", nullable = false)
  private MediaContent media;

  @Column(name = "hex_color", length = 7)
  private String hexColor;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  @JsonIgnoreProperties("categories")
  private Set<Activity> activities;
}