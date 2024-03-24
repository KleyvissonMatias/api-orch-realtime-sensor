package br.com.orchestrator.realtime.sensor.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
@AllArgsConstructor
public class AppPropertiesConfiguration {
    private AppProperties appProperties;
}
