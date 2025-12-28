package org.barmejha.domain.entities.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.logging.Log;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.ws.rs.InternalServerErrorException;

import java.util.Collections;
import java.util.Set;

@Converter(autoApply = true)
public class TimeRangeConverter implements AttributeConverter<Set<TimeRange>, String> {

  private static final ObjectMapper OBJECT_MAPPER;

  static {
    OBJECT_MAPPER = new ObjectMapper();
    OBJECT_MAPPER.registerModule(new JavaTimeModule());
  }

  private static final TypeReference<Set<TimeRange>> TYPE_REFERENCE =
      new TypeReference<>() {};

  @Override
  public String convertToDatabaseColumn(Set<TimeRange> ranges) {
    if (ranges == null || ranges.isEmpty()) return null;
    try {
      Log.debugf("Converting TimeRange set to JSON for database storage", ranges);
      return OBJECT_MAPPER.writeValueAsString(ranges);
    } catch (JsonProcessingException e) {
      throw new InternalServerErrorException(
          "Error converting TimeRange set to JSON for database storage", e
      );
    }
  }

  @Override
  public Set<TimeRange> convertToEntityAttribute(String json) {
    if (json == null || json.trim().isEmpty()) {
      return Collections.emptySet();
    }
    try {
      Log.infof("Converting JSON to TimeRange set. JSON: %s", json);
      return OBJECT_MAPPER.readValue(json, TYPE_REFERENCE);
    } catch (JsonProcessingException e) {
      Log.errorf(e, "Error converting JSON to TimeRange set. JSON: %s", json);
      return Collections.emptySet();
    }
  }
}