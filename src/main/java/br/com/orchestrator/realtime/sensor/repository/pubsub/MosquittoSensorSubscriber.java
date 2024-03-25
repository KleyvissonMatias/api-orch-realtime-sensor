package br.com.orchestrator.realtime.sensor.repository.pubsub;

import br.com.orchestrator.realtime.sensor.config.PubSubInputChannelConfiguration;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MosquittoSensorSubscriber {
    @ServiceActivator(inputChannel = PubSubInputChannelConfiguration.MOSQUITTO_SENSOR_INPUT_CHANNEL, autoStartup = "true")
    public void messageReceiverMosquittoSensor(
            String payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {

        if (payload == null || payload.isEmpty()) {
            log.error("Mensagem recebida com payload nulo ou vazio. Mensagem não processada. message: " + message);
            message.nack();
            return;
        }

        log.info("Mensagem recebida mosquitto_sensor_topic - Payload: " + payload);
        message.ack();
    }
}
