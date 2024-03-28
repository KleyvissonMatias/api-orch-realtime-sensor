package br.com.orchestrator.realtime.sensor.repository.impl;

import br.com.orchestrator.realtime.sensor.config.AppProperties;
import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;
import br.com.orchestrator.realtime.sensor.repository.IMosquittoSensorRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class MosquittoSensorRepositoryImpl implements IMosquittoSensorRepository {

    private final AppProperties appProperties;

    private final RestTemplate restTemplate;

    @Override
    @Retry(name = "enviarDadosSensorTemperaturaParaAtomico", fallbackMethod = "fallbackEnviarDadosSensorTemperaturaParaAtomico")
    public void enviarDadosSensorTemperaturaParaAtomico(SensorTempEventDTO eventDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SensorTempEventDTO> requestEntity = new HttpEntity<>(eventDTO, headers);

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    appProperties.getAtomSensorUrl(),
                    HttpMethod.POST,
                    requestEntity,
                    Void.class
            );
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                log.info("[ENVIADO] => [enviarDadosSensorTemperaturaParaAtomico] - Status code: {} - Payload: [{}]"
                        , statusCode, eventDTO);
            } else {
                log.error("[ERRO] => [enviarDadosSensorTemperaturaParaAtomico] - Status code: {}", statusCode);
            }
        } catch (HttpClientErrorException e) {
            log.error("[ERRO] => [enviarDadosSensorTemperaturaParaAtomico] - [{}]", e.getMessage());
        }
    }

    @Override
    public void fallbackEnviarDadosSensorTemperaturaParaAtomico(SensorTempEventDTO eventDTO, Exception e) {
        log.warn("[FALLBACK] => [enviarDadosSensorTemperaturaParaAtomico] - [{}] - {}", eventDTO, e);
    }
}


