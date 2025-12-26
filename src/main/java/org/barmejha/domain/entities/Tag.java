package org.barmejha.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.enums.TagType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity
@Cacheable
@Table(name = "tags")
public class Tag extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 50)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TagType type;
}