package org.barmejha.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "schedules")
public class Schedule extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "activity_id", nullable = false)
  public Activity activity;

  @Column(nullable = false)
  public LocalDate startDate;

  public LocalDate endDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  public RecurrencePattern recurrence;

  @Convert(converter = DaysOfWeekConverter.class)
  @Column(columnDefinition = "TEXT")
  public Set<DayOfWeek> recurringDays;

  @Column(nullable = false)
  public LocalTime startTime;

  @Column(nullable = false)
  public LocalTime endTime;

  @Convert(converter = TimeRangeConverter.class)
  @Column(columnDefinition = "TEXT")
  public Set<TimeRange> breaks;

  @Convert(converter = LocalDateConverter.class)
  @Column(columnDefinition = "TEXT")
  public Set<LocalDate> excludedDates;
}

