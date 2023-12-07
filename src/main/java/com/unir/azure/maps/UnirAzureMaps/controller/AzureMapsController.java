package com.unir.azure.maps.UnirAzureMaps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unir.azure.maps.UnirAzureMaps.dto.AzureRequestDto;
import com.unir.azure.maps.UnirAzureMaps.dto.AzureResponseDto;
import com.unir.azure.maps.UnirAzureMaps.service.AzureMapsServiceImp;

@RestController
@RequestMapping("/unir/azure/maps/routes/simulator")
public class AzureMapsController {

  @Autowired
  private AzureMapsServiceImp azureMapsService;

  @PostMapping("/v1")
  public ResponseEntity<AzureResponseDto> azureMapsRoutesSimulator(
      @RequestBody AzureRequestDto azureRequestDto) throws Exception {
    var azureResponse =
        ResponseEntity.ok().body(azureMapsService.getRouteCoordinatesAzureMaps(azureRequestDto));
    return azureResponse;
  }

}
