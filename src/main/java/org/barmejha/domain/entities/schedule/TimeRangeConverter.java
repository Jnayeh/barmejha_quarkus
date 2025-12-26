package org.barmejha.domain.entities.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.ws.rs.InternalServerErrorException;

import java.util.Set;

@Converter
public class TimeRangeConverter implements AttributeConverter<Set<TimeRange>, String> {
  @Override
  public String convertToDatabaseColumn(Set<TimeRange> ranges) {
    try {
      if (ranges == null) {
        return null;
      }
      return new ObjectMapper().writeValueAsString(ranges);
    } catch (JsonProcessingException e) {
      throw new InternalServerErrorException("Error processing time range json", e);
    }
  }

  @Override
  public Set<TimeRange> convertToEntityAttribute(String json) {
    try {
      return new ObjectMapper().readValue(json, new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      Log.error("Error processing days of the week json", e);
      return Set.of();
    }
  }
}
