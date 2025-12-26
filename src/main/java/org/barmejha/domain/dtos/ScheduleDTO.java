package org.barmejha.domain.dtos;

import org.barmejha.domain.dtos.utils.DTOUtils;
import org.barmejha.domain.entities.Schedule;
import org.barmejha.domain.entities.schedule.TimeRange;
import org.barmejha.domain.enums.RecurrencePattern;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public record ScheduleDTO(
    Long id,
    ActivityDTO activity,
    LocalDate startDate,
    LocalDate endDate,
    RecurrencePattern recurrence,
    LocalTime startTime,
    LocalTime endTime,
    Set<TimeRange> breaks,
    Set<DayOfWeek> recurringDays,
    Set<LocalDate> excludedDates) {
  public static ScheduleDTO fromEntity(Schedule entity, List<String> joins, String lang) {
    if (entity == null) return null;

    return new ScheduleDTO(
        entity.getId(),
        ActivityDTO.fromEntity(entity.getActivity(), joins, lang),
        entity.getStartDate(),
        entity.getEndDate(),
        entity.getRecurrence(),
        entity.getStartTime(),
        entity.getEndTime(),
        DTOUtils.mapToSetIfInitialized(entity.getBreaks(), breakEntity -> breakEntity),
        DTOUtils.mapToSetIfInitialized(entity.getRecurringDays(), day -> day),
        DTOUtils.mapToSetIfInitialized(entity.getExcludedDates(), date -> date));
  }

  public static Set<ScheduleDTO> mapToSetIfInitialized(Set<Schedule> entities, List<String> joins, String lang) {
    return DTOUtils.mapToSetIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }

  public static List<ScheduleDTO> mapToListIfInitialized(List<Schedule> entities, List<String> joins, String lang) {
    return DTOUtils.mapIfInitialized(entities, e -> fromEntity(e,  joins, lang));
  }
}
