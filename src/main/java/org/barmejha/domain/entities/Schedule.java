package org.barmejha.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.schedule.DaysOfWeekConverter;
import org.barmejha.domain.entities.schedule.LocalDateConverter;
import org.barmejha.domain.entities.schedule.TimeRange;
import org.barmejha.domain.entities.schedule.TimeRangeConverter;
import org.barmejha.domain.enums.RecurrencePattern;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Entity(name = "schedules")
public class Schedule extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "activity_id", nullable = false)
  @JsonBackReference
  private Activity activity;

  @Column(nullable = false, name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RecurrencePattern recurrence;

  @Convert(converter = DaysOfWeekConverter.class)
  @Column(columnDefinition = "TEXT", name = "recurring_days")
  private Set<DayOfWeek> recurringDays;

  @Column(nullable = false, name = "start_time")
  private LocalTime startTime;

  @Column(nullable = false, name = "end_time")
  private LocalTime endTime;

  @Convert(converter = TimeRangeConverter.class)
  @Column(columnDefinition = "TEXT")
  private Set<TimeRange> breaks;

  @Convert(converter = LocalDateConverter.class)
  @Column(columnDefinition = "TEXT", name = "excluded_dates")
  private Set<LocalDate> excludedDates;
}

