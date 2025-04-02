package org.barmejha.domain.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.barmejha.domain.entities.schedule.TimeRange;
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
public class ScheduleDTO {

  private Long id;

  private ActivityDTO activity;

  private LocalDate startDate;

  private LocalDate endDate;

  private RecurrencePattern recurrence;

  private Set<DayOfWeek> recurringDays;

  private LocalTime startTime;

  private LocalTime endTime;

  private Set<TimeRange> breaks;

  private Set<LocalDate> excludedDates;
}

