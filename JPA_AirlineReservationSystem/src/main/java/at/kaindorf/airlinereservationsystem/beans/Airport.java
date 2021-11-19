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
        //@NamedQuery(name = "Airport.getAllAirportsOfCity", query = "SELECT a FROM Airport a WHERE a.city = (:city)"),
        @NamedQuery(name = "Airport.getAllDepartureFlightsOfAirport", query = "SELECT df FROM Airport a JOIN a.departureFlights df")
})
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "airport_id")
    private Long airportId;

    @NonNull
    @Column(length = 60)
    private String country;

    @NonNull
    @Column(length = 50)
    private String city;

    @NonNull
    @Column(length = 100)
    private String name;

    @ManyToMany(mappedBy = "airports")
    @ToString.Exclude
    private List<Aircraft> aircraftList = new ArrayList<>();;

    @OneToMany(mappedBy = "arrivalAirport", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> arrivalFlights = new ArrayList<>();;

    @OneToMany(mappedBy = "departureAirport",orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> departureFlights = new ArrayList<>();;
}
