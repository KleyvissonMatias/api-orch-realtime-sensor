package br.com.orchestrator.realtime.sensor.repository.pubsub;

import br.com.orchestrator.realtime.sensor.config.PubSubInputChannelConfiguration;
import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;
import br.com.orchestrator.realtime.sensor.service.IMosquittoSensorSevice;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class MosquittoTemperatureSensorSubscriber {

    private final IMosquittoSensorSevice sensorSevice;

    @ServiceActivator(inputChannel = PubSubInputChannelConfiguration.MOSQUITTO_SENSOR_INPUT_CHANNEL, autoStartup = "true")
    public void messageReceiverMosquittoSensorTemperature(
            SensorTempEventDTO sensorTempEventDTO,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {

        if (isNullOrInvalid(sensorTempEventDTO)) {
            handleInvalidMessage(message);
            return;
        }

        log.info("[BEGIN] => [messageReceiverMosquittoSensorTemperature] - [TOPIC] mosquitto_sensor_topic - Payload: [{}]",
                sensorTempEventDTO);
        sensorSevice.sendTemperatureSensorDataToAtomic(sensorTempEventDTO);
        message.ack();
        log.info("[SIGNED] => [messageReceiverMosquittoSensorTemperature] - [TOPIC] mosquitto_sensor_topic - Payload: [{}]",
                sensorTempEventDTO);
    }

    private boolean isNullOrInvalid(SensorTempEventDTO sensorTempEventDTO) {
        return Objects.isNull(sensorTempEventDTO) || sensorTempEventDTO.isInvalid();
    }

    private void handleInvalidMessage(BasicAcknowledgeablePubsubMessage message) {
        log.warn("[FAIL] => payload nulo ou invalido. Mensagem não processada. message: [{}]", message);
        message.nack();
    }
}
