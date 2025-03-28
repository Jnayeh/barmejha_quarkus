package org.barmejha.domain.entities.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.ws.rs.InternalServerErrorException;

import java.time.DayOfWeek;
import java.util.Set;

@Converter
public class DaysOfWeekConverter implements AttributeConverter<Set<DayOfWeek>, String> {
  @Override
  public String convertToDatabaseColumn(Set<DayOfWeek> days) {
    try {
      return new ObjectMapper().writeValueAsString(days);
    } catch (JsonProcessingException e) {
      throw new InternalServerErrorException("Error processing days of the week json", e);
    }
  }

  @Override
  public Set<DayOfWeek> convertToEntityAttribute(String json) {
    try {
      return new ObjectMapper().readValue(json, new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      Log.error("Error processing days of the week json", e);
      return null;
    }
  }
}

