package com.unir.azure.maps.UnirAzureMaps.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.models.GeoPosition;
import com.azure.maps.search.MapsSearchClient;
import com.azure.maps.search.MapsSearchClientBuilder;
import com.azure.maps.search.models.FuzzySearchOptions;
import com.azure.maps.search.models.MapsSearchAddress;
import com.azure.maps.search.models.SearchAddressResult;
import com.azure.maps.search.models.SearchAddressResultItem;
import com.unir.azure.maps.UnirAzureMaps.dto.AzureRequestDto;
import com.unir.azure.maps.UnirAzureMaps.dto.AzureResponseDto;
import com.unir.azure.maps.UnirAzureMaps.dto.AzureRouteDto;
import com.unir.azure.maps.UnirAzureMaps.util.JacksonUtil;

@Service
public class AzureMapsServiceImp {

  private static final String AZURE_MAPS_KEY = "3IW5c7uRW3wbdaRIw8BXxaNGHNCuJsgUk2rBpkLtGcU";

  @Autowired
  private IoTDeviceService ioTDeviceService;

  public AzureResponseDto getRouteCoordinatesAzureMaps(AzureRequestDto azureRequestDto)
      throws Exception {
    MapsSearchClientBuilder builder = new MapsSearchClientBuilder();
    AzureKeyCredential keyCredential = new AzureKeyCredential(AZURE_MAPS_KEY);
    builder.credential(keyCredential);
    MapsSearchClient client = builder.buildClient();
    List<String> countryFilter = new ArrayList<>();
    countryFilter.add("EC");
    SearchAddressResult results =
        client.fuzzySearch(new FuzzySearchOptions(azureRequestDto.getStreetName(), countryFilter));
    var azureResponse = AzureResponseDto.builder().build();
    for (SearchAddressResultItem item : results.getResults()) {
      MapsSearchAddress address = item.getAddress();
      GeoPosition coordinate = item.getPosition();
      List<AzureRouteDto> azureRouteDataList = new ArrayList<>();
      azureRouteDataList.add(AzureRouteDto.builder().streetNumber(address.getStreetNumber())
          .country(address.getCountry()).streetName(address.getStreetName())
          .countryCode(address.getCountryCode()).postalCode(address.getPostalCode())
          .municipality(address.getMunicipality())
          .latitude(String.valueOf(coordinate.getLatitude())).amountCheese("80")
          .quantityWineBottles("200").longitude(String.valueOf(coordinate.getLongitude())).build());
      azureResponse.setData(azureRouteDataList);
    }

    ioTDeviceService.sendMessage(JacksonUtil.toString(azureResponse));
    return azureResponse;
  }
}

