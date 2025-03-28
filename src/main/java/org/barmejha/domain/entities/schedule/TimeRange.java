package org.barmejha.domain.entities.schedule;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeRange {
  @Id
  private Long id;

  private LocalTime start;
  private LocalTime end;

}