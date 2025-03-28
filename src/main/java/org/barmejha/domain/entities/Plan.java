package org.barmejha.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.communities.Comment;
import org.barmejha.domain.entities.users.Client;
import org.barmejha.domain.entities.users.User;
import org.barmejha.domain.enums.PaymentType;
import org.barmejha.domain.enums.PlanStatus;
import org.barmejha.domain.enums.PlanType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "plans")
public class Plan extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "schedule_id", nullable = false)
  public Schedule schedule;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  public Client client;
  @Column(nullable = false)
  public LocalDateTime startTime;
  @Column(nullable = false)
  public LocalDateTime endTime;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  public PlanStatus status;
  @Column(nullable = false)
  public BigDecimal finalPrice;
  @Enumerated(EnumType.STRING)
  @Builder.Default
  public PlanType type = PlanType.PRIVATE;
  @Builder.Default
  public PaymentType paymentType;
  public String title;
  @Column(length = 1000)
  public String description;
  @ManyToMany(fetch = FetchType.LAZY)
  public Set<User> participants;
  @OneToMany(fetch = FetchType.LAZY)
  public Set<Comment> comments;
  @Min(1)
  @Column(nullable = false)
  @Builder.Default
  public int minParticipants = 1;
  public int maxParticipants;

}