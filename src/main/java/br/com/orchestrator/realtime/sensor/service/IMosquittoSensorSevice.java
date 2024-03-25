package br.com.orchestrator.realtime.sensor.service;

import br.com.orchestrator.realtime.sensor.domain.SensorEventDTO;

public interface IMosquittoSensorSevice {
    void enviarDadosParaAtomico(SensorEventDTO sensorEventDTO);
}
