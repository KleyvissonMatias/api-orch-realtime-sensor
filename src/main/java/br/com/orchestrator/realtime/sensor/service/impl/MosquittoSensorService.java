package br.com.orchestrator.realtime.sensor.service.impl;

import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;
import br.com.orchestrator.realtime.sensor.repository.IMosquittoSensorRepository;
import br.com.orchestrator.realtime.sensor.service.IMosquittoSensorSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MosquittoSensorService implements IMosquittoSensorSevice {

    @Autowired
    IMosquittoSensorRepository sensorRepository;

    @Override
    public void enviarDadosSensorParaAtomico(SensorTempEventDTO sensorTempEventDTO) {
        log.info("[ENVIAR] - [enviarDadosSensorParaAtomico] Payload: [{}]", sensorTempEventDTO);
        sensorRepository.enviarDadosSensorTemperaturaParaAtomico(sensorTempEventDTO);
    }
}
