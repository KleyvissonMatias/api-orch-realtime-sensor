package br.com.orchestrator.realtime.sensor.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SensorTempEventDTO {
    private String cliente;
    private long microsegundos;
    private LocalDateTime dataAtual;
    private String timestamp;
    private float celcius;
    private float fahrenheit;
    private float kelvin;

    private static final String uuid = UUID.randomUUID().toString();

    public boolean isInvalid() {
        if (cliente == null || cliente.isEmpty()) return true;
        if (dataAtual == null) return true;
        if (timestamp == null || timestamp.isEmpty()) return true;
        return false;
    }
}
