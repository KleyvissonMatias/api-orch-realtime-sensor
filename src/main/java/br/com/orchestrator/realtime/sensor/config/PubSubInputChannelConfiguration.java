package br.com.orchestrator.realtime.sensor.config;

import br.com.orchestrator.realtime.sensor.domain.SensorTempEventDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubMessageSource;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.Assert;

@EnableConfigurationProperties(PubSubProperties.class)
@Configuration
@RequiredArgsConstructor
@Slf4j
public class PubSubInputChannelConfiguration {

    private final PubSubProperties pubSubProperties;
    public static final String MOSQUITTO_SENSOR_INPUT_CHANNEL = "mosquittoSensorInputChannel";

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper;
    }

    @Bean
    @Primary
    public PubSubMessageConverter pubSubMessageConverter(ObjectMapper objectMapper) {
        return new JacksonPubSubMessageConverter(objectMapper);
    }

    @Bean(MOSQUITTO_SENSOR_INPUT_CHANNEL)
    public MessageChannel mosquittoSensorMessageChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    @InboundChannelAdapter(channel = MOSQUITTO_SENSOR_INPUT_CHANNEL, poller = @Poller(fixedDelay = "100"))
    public MessageSource<Object> mosquittoTemperatureSensorChannelAdapter(PubSubTemplate pubSubTemplate) {
        String subscription = pubSubProperties.getSubscriptions().get(MOSQUITTO_SENSOR_INPUT_CHANNEL);

        Assert.notNull(
                subscription,
                String.format("[SUBSCRIPTION] ['%s'] não mapeada. Validar configuração das properties",
                        MOSQUITTO_SENSOR_INPUT_CHANNEL)
        );

        PubSubInputChannelConfiguration.log.info("[INPUT CHANNEL] => [{}]", MOSQUITTO_SENSOR_INPUT_CHANNEL);
        PubSubMessageSource messageSource =
                new PubSubMessageSource(pubSubTemplate, subscription);
        messageSource.setAckMode(AckMode.MANUAL);
        messageSource.setPayloadType(SensorTempEventDTO.class);
        return messageSource;
    }
}
