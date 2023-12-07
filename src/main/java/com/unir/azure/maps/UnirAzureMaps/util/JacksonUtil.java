package com.unir.azure.maps.UnirAzureMaps.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


  private JacksonUtil() {

  }

  public static String toString(Object value) {
    try {
      return OBJECT_MAPPER.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(
          "The given Json object value: " + value + " cannot be transformed to a String");
    }
  }



}

