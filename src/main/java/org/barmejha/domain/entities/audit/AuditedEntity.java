package org.barmejha.domain.entities.audit;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;

@MappedSuperclass
public abstract class AuditedEntity extends PanacheEntityBase {

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @Column(name = "updated_at")
  private Instant updatedAt;

  @Column(name = "updated_by")
  private String updatedBy;

  @PrePersist
  void onCreate() {
    this.createdAt = Instant.now();
    // Populate createdBy from security context
  }

  @PreUpdate
  void onUpdate() {
    this.updatedAt = Instant.now();
    // Populate updatedBy from security context
  }
}