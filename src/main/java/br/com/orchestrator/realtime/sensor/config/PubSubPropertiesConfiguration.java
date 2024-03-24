package br.com.orchestrator.realtime.sensor.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PubSubProperties.class)
@AllArgsConstructor
public class PubSubPropertiesConfiguration {
    private PubSubProperties pubSubProperties;
}
