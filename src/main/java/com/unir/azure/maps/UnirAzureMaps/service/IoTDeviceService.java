package com.unir.azure.maps.UnirAzureMaps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.Message;

@Service
public class IoTDeviceService {
  private static final String CONNECTION_STRING =
      "HostName=UnirAct1Hub.azure-devices.net;DeviceId=Act3Device;SharedAccessKey=4drY1LFnlqx2d38xiqT/BnHkFQXn4VFNpAIoTPVKMyk=";
  private static final Logger LOGGER = LoggerFactory.getLogger(IoTDeviceService.class);

  private DeviceClient deviceClient;

  public IoTDeviceService() throws Exception {
    deviceClient = new DeviceClient(CONNECTION_STRING, IotHubClientProtocol.MQTT);
    deviceClient.open(true);
  }

  public void sendMessage(String message) throws Exception {
    Message msg = new Message(message);
    deviceClient.sendEventAsync(msg, null, null);
    LOGGER.info("Mensaje enviado: {}", message);
  }

  public void close() throws Exception {
    deviceClient.close();
  }

}
