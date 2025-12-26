package org.barmejha.domain.entities.communities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.barmejha.domain.entities.Plan;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.users.User;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity
@Table(name = "comments")
public class Comment extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  @JsonBackReference
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plan_id")
  @JsonBackReference
  private Plan plan;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  @JsonBackReference
  private User author;

  @Column(columnDefinition = "TEXT")
  private String content;
}
