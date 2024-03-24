package br.com.orchestrator.realtime.sensor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "api.atom.sensor")
public class AppProperties {
    private String atomSensorUrl;
}
