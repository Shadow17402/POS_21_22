package at.kaindorf.weatherstation.beans;

import at.kaindorf.weatherstation.json.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Weatherdata {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime dateTime;
    private float temperature;
    private float pressure;
    private float humidity;
    private int windSpeed;
    private int windDirection;

}