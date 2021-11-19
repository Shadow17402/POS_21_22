package at.kaindorf.airlinereservationsystem.beans;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class AircraftType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aircraft_type_id")
    private Long aircraftTypeId;

    @NonNull
    @Column(name = "type_name", length = 50)
    private String typeName;

    @NonNull
    @Column(name = "seats")
    private int seats;
}
