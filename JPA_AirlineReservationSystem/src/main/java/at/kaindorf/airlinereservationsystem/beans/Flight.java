package at.kaindorf.airlinereservationsystem.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Flight.getAllArrivalFlightsOfAirport", query = "SELECT f FROM Flight f  WHERE f.arrivalAirport.name = (:name)"),
        @NamedQuery(name = "Flight.getAllDepartureFlightsOfAirport", query = "SELECT f FROM Flight f  WHERE f.departureAirport.name = (:name)"),
        @NamedQuery(name = "Flight.getAllFlightsOfAirline", query = "SELECT f FROM Flight f  WHERE f.airline.airlineName = (:airlineName)"),
        @NamedQuery(name = "Flight.getAllFlightsOfArrivalCountry", query = "SELECT f FROM Flight f  WHERE f.arrivalAirport.country = (:country)"),
        @NamedQuery(name = "Flight.getAllFlightsOfDepartureCountry", query = "SELECT f FROM Flight f  WHERE f.departureAirport.country = (:country)")
})
public class Flight implements Serializable {
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long flightId;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Airline airline;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport")
    private Airport departureAirport;

    @NonNull
    @Column(name = "departure_time")
    private LocalTime departureTime;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport")
    private Airport arrivalAirport;

    @NonNull
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
}
