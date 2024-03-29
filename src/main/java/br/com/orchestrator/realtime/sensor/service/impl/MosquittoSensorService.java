package br.com.orchestrator.realtime.sensor.service.impl;

import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;
import br.com.orchestrator.realtime.sensor.repository.IMosquittoTemperatureSensorRepository;
import br.com.orchestrator.realtime.sensor.service.IMosquittoSensorSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MosquittoSensorService implements IMosquittoSensorSevice {

    @Autowired
    IMosquittoTemperatureSensorRepository sensorRepository;

    @Override
    public void sendTemperatureSensorDataToAtomic(SensorTempEventDTO sensorTempEventDTO) {
        log.info("[SEND] => [sendTemperatureSensorDataToAtomic] Payload: [{}]", sensorTempEventDTO);
        sensorRepository.sendTemperatureSensorDataToAtomic(sensorTempEventDTO);
    }
}
