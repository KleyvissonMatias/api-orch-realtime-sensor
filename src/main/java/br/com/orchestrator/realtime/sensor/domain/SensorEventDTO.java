package br.com.orchestrator.realtime.sensor.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorEventDTO {
    private String cliente;
    private String microsegundos;
    private String dataAtual;
    private String timestamp;
    private String celcius;
    private String fahrenheit;
    private String kelvin;
}
