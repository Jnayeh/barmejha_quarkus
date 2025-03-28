package org.barmejha.domain.entities;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.enums.TagType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
@Table(name = "tags")
public class Tag extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(unique = true, nullable = false, length = 50)
  public String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  public TagType type;
}