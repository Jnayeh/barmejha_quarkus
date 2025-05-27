package org.barmejha.domain.entities.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeRange {

  private LocalTime start;
  private LocalTime end;

}