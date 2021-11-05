package at.kaindorf.airlinereservationsystem.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirlinePK implements Serializable {
    private Long airlineId;
    private String airlineName;
}
