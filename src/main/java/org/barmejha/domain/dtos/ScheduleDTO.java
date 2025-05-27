package org.barmejha.domain.dtos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Schedule;
import org.barmejha.domain.entities.schedule.TimeRange;
import org.barmejha.domain.enums.RecurrencePattern;

public record ScheduleDTO(
    Long id,
    ActivityDTO activity,
    LocalDate startDate,
    LocalDate endDate,
    RecurrencePattern recurrence,
    Set<DayOfWeek> recurringDays,
    LocalTime startTime,
    LocalTime endTime,
    Set<TimeRange> breaks,
    Set<LocalDate> excludedDates) {
  public static ScheduleDTO fromEntity(Schedule entity, String lang) {
    if (entity == null) return null;

    return new ScheduleDTO(
        entity.getId(),
        ActivityDTO.fromEntity(entity.getActivity(), lang),
        entity.getStartDate(),
        entity.getEndDate(),
        entity.getRecurrence(),
        DTOUtils.mapToSetIfInitialized(entity.getRecurringDays(), day -> day),
        entity.getStartTime(),
        entity.getEndTime(),
        DTOUtils.mapToSetIfInitialized(entity.getBreaks(), breakEntity -> breakEntity),
        DTOUtils.mapToSetIfInitialized(entity.getExcludedDates(), date -> date));
  }

  public static Set<ScheduleDTO> mapToSetIfInitialized(Set<Schedule> entities, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e, lang));
  }

  public static List<ScheduleDTO> mapToListIfInitialized(List<Schedule> entities, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e, lang));
  }
}
