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
@NamedQueries({
        @NamedQuery(name = "Aircraft.getAllAircraftOfAirport", query = "SELECT a FROM Aircraft a JOIN a.airports ap WHERE ap.name = (:airport)"),
        @NamedQuery(name = "Aircraft.getAllAircraftOfAirline", query = "SELECT a FROM Aircraft a WHERE a.airline.airlineName = (:airlineName)"),
        //@NamedQuery(name = "Aircraft.getAllFlightIDFromAircraft", query = "SELECT f.flightId FROM Aircraft a JOIN a.flightList f")
        @NamedQuery(name = "Aircraft.getAllAircraftOfType", query = "SELECT a FROM Aircraft a WHERE LOWER(a.aircraftType.typeName) LIKE LOWER(:typename)"),
})
public class Aircraft implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aircraft_id")
    private Long aircraftId;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Airline airline;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_type_id")
    private AircraftType aircraftType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="aircraft_airport", joinColumns ={@JoinColumn(name="aircraft_id")}, inverseJoinColumns ={@JoinColumn(name="airport_id")})
    @ToString.Exclude
    private List<Airport> airports = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "aircraft", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> flightList = new ArrayList<>();
}
