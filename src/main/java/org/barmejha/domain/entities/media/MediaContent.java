package org.barmejha.domain.entities.media;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.enums.MediaType;
import org.barmejha.domain.id.USID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity(name = "media_content")
public class MediaContent extends AuditedEntity {
  @Id
  @USID
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String url;

  @Enumerated(EnumType.STRING)
  private MediaType type;
}