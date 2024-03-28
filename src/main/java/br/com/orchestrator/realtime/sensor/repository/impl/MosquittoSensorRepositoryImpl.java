package br.com.orchestrator.realtime.sensor.repository.impl;

import br.com.orchestrator.realtime.sensor.config.AppProperties;
import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;
import br.com.orchestrator.realtime.sensor.repository.IMosquittoSensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class MosquittoSensorRepositoryImpl implements IMosquittoSensorRepository {

    @Autowired
    AppProperties appProperties;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void enviarDadosSensorTemperaturaParaAtomico(SensorTempEventDTO eventDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SensorTempEventDTO> requestEntity = new HttpEntity<>(eventDTO, headers);

        try {
            ResponseEntity<Void> responseEntity = this.restTemplate.exchange(
                    this.appProperties.getAtomSensorUrl(),
                    HttpMethod.POST,
                    requestEntity,
                    Void.class
            );
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                log.info("[ENVIADO] - [enviarDadosSensorTemperaturaParaAtomico] - [{}]", statusCode);
            } else {
                log.error("[ERRO] - [enviarDadosSensorTemperaturaParaAtomico] - Código de status: {}", statusCode);
            }
        } catch (HttpClientErrorException e) {
            log.error("[ERRO] - [enviarDadosSensorTemperaturaParaAtomico] - [{}]", e.getMessage());
        }
    }

    @Override
    public void fallbackEnviarDadosSensorTemperaturaParaAtomico(SensorTempEventDTO eventDTO, Exception e) {
        log.warn("[FALLBACK] - [enviarDadosSensorTemperaturaParaAtomico] - [{}] - [{}]", eventDTO, e);
    }
}
