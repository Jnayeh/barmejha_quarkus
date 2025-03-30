package org.barmejha.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.entities.audit.AuditedEntity;
import org.barmejha.domain.entities.schedule.DaysOfWeekConverter;
import org.barmejha.domain.entities.schedule.LocalDateConverter;
import org.barmejha.domain.entities.schedule.TimeRange;
import org.barmejha.domain.entities.schedule.TimeRangeConverter;
import org.barmejha.domain.enums.RecurrencePattern;
import org.barmejha.domain.idgenerator.USID;

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
  @USID
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "activity_id", nullable = false)
  private Activity activity;

  @Column(nullable = false)
  private LocalDate startDate;

  private LocalDate endDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RecurrencePattern recurrence;

  @Convert(converter = DaysOfWeekConverter.class)
  @Column(columnDefinition = "TEXT")
  private Set<DayOfWeek> recurringDays;

  @Column(nullable = false)
  private LocalTime startTime;

  @Column(nullable = false)
  private LocalTime endTime;

  @Convert(converter = TimeRangeConverter.class)
  @Column(columnDefinition = "TEXT")
  private Set<TimeRange> breaks;

  @Convert(converter = LocalDateConverter.class)
  @Column(columnDefinition = "TEXT")
  private Set<LocalDate> excludedDates;
}

