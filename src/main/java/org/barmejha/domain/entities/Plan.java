package org.barmejha.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.*;
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
@Valid
@Entity
@Table(name = "plans")
public class Plan extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "schedule_id", nullable = false)
  @JsonBackReference
  private Schedule schedule;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  @JsonBackReference
  private Client client;

  @Column(nullable = false, name = "start_time")
  private LocalDateTime startTime;

  @Column(nullable = false, name = "end_time")
  private LocalDateTime endTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PlanStatus status;

  @Column(nullable = false, name = "final_price")
  private BigDecimal finalPrice;

  @Enumerated(EnumType.STRING)
  @Builder.Default
  private PlanType type = PlanType.PRIVATE;

  @Builder.Default
  @Column(name = "payment_type")
  private PaymentType paymentType = PaymentType.CREATOR;

  private String title;

  @Column(length = 1000)
  private String description;

  @ManyToMany(fetch = FetchType.LAZY)
  @JsonManagedReference
  private Set<User> participants;

  @OneToMany(fetch = FetchType.LAZY)
  @JsonManagedReference
  private Set<Comment> comments;

  @Min(1)
  @Column(name = "min_participants", nullable = false)
  @Builder.Default
  private int minParticipants = 1;

  @Column(name = "max_participants")
  private int maxParticipants;

}