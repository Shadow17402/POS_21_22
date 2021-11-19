package at.kaindorf.airlinereservationsystem.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@IdClass(AirlinePK.class)
@NamedQueries({
        @NamedQuery(name = "Airline.getAllAirlinesOfAircraftType", query = "SELECT a FROM Airline a JOIN a.aircraftList al WHERE al.aircraftType.typeName = (:name)")
})
public class Airline implements Serializable {
    @Id
    @NonNull
    @Column(name = "airline_id")
    private Long airlineId;

    @Id
    @NonNull
    @Column(name = "airline_name", length = 255)
    private String airlineName;

    @OneToMany(mappedBy = "airline", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Flight> flights = new ArrayList<>();;

    @OneToMany(mappedBy = "airline")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Aircraft> aircraftList = new ArrayList<>();;
}
