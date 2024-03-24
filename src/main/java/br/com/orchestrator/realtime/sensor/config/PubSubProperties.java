package br.com.orchestrator.realtime.sensor.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "gcp.pubsub")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PubSubProperties {
    private Map<String, String> subscriptions;
    private Map<String, String> topics;
}
