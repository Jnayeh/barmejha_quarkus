package org.barmejha.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
import org.barmejha.domain.idgenerator.USID;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity
@Table(name = "plans")
public class Plan extends AuditedEntity {

  @Id
  @USID
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "schedule_id", nullable = false)
  private Schedule schedule;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;
  @Column(nullable = false)
  private LocalDateTime startTime;
  @Column(nullable = false)
  private LocalDateTime endTime;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PlanStatus status;
  @Column(nullable = false)
  private BigDecimal finalPrice;
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private PlanType type = PlanType.PRIVATE;
  @Builder.Default
  private PaymentType paymentType = PaymentType.CREATOR;
  private String title;
  @Column(length = 1000)
  private String description;
  @ManyToMany(fetch = FetchType.LAZY)
  private Set<User> participants;
  @OneToMany(fetch = FetchType.LAZY)
  private Set<Comment> comments;
  @Min(1)
  @Column(nullable = false)
  @Builder.Default
  private int minParticipants = 1;
  private int maxParticipants;

}