package br.com.orchestrator.realtime.sensor.repository;

import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface IMosquittoSensorRepository {
    void sendTemperatureSensorDataToAtomic(SensorTempEventDTO eventDTO);
    void fallbackSendTemperatureSensorDataToAtomic(SensorTempEventDTO eventDTO, Exception e);
}
