package org.barmejha.domain.entities.media;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.enums.MediaType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "media_content")
public class MediaContent extends AuditedEntity {
  @Id
  public Long id;

  @Column(columnDefinition = "TEXT")
  public String url;

  @Enumerated(EnumType.STRING)
  public MediaType type;
}