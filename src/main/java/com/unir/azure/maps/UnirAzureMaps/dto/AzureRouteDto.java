package com.unir.azure.maps.UnirAzureMaps.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AzureRouteDto {

  private String streetNumber;
  private String country;
  private String streetName;
  private String countryCode;
  private String postalCode;
  private String municipality;
  private String latitude;
  private String longitude;
  private String amountCheese;
  private String quantityWineBottles;
}
