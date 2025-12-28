package org.barmejha.domain.entities.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.logging.Log;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.ws.rs.InternalServerErrorException;

import java.time.LocalDate;
import java.util.Set;

@Converter
public class LocalDateConverter implements AttributeConverter<Set<LocalDate>, String> {
  @Override
  public String convertToDatabaseColumn(Set<LocalDate> days) {
    if (days == null) return null;
    try {
      return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(days);
    } catch (JsonProcessingException e) {
      throw new InternalServerErrorException("Error processing the date json: ", e);
    }
  }

  @Override
  public Set<LocalDate> convertToEntityAttribute(String json) {
    try {
      return new ObjectMapper().registerModule(new JavaTimeModule()).readValue(json, new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      Log.error("Error processing the date json", e);
      return Set.of();
    }
  }
}

