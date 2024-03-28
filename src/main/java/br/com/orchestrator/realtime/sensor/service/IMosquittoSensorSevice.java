package br.com.orchestrator.realtime.sensor.service;

import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;

public interface IMosquittoSensorSevice {
    void enviarDadosSensorParaAtomico(SensorTempEventDTO sensorTempEventDTO);
}
