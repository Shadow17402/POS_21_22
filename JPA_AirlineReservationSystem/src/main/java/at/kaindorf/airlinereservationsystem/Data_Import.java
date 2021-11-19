package at.kaindorf.airlinereservationsystem;

import at.kaindorf.airlinereservationsystem.beans.*;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class Data_Import {

    Random random = new Random();

    private static final Path pathAircraftType = Paths.get(System.getProperty("user.dir"), "src", "main", "java","at","kaindorf","airlinereservationsystem","res", "aircrafttypes.csv");
    private static final Path pathAirlines = Paths.get(System.getProperty("user.dir"), "src", "main", "java","at","kaindorf","airlinereservationsystem","res", "airlines.csv");
    private static final Path pathAirports = Paths.get(System.getProperty("user.dir"), "src", "main", "java","at","kaindorf","airlinereservationsystem","res", "airports.csv");

    private Set<AircraftType> aircraftTypeSet = new HashSet<>();
    private List<Airline> airlineList = new ArrayList<>();
    private List<Airport> airportList = new ArrayList<>();

    private List<Aircraft> aircraftList = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();

    public static Set<String> readFile(Path path) {

        Set<String> dataSet = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
            dataSet = br.lines().skip(1).map(String::toString).collect(Collectors.toSet());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    public void createAirlines() {
        Set<String> dataSet = readFile(pathAirlines);

        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            airlineList.add(new Airline(Long.parseLong(stringArray[0]), stringArray[1]));
        });
    }

    public void createAirports() {
        Set<String> dataSet = readFile(pathAirports);
        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            airportList.add(new Airport(stringArray[8], stringArray[10], stringArray[3]));
        });

        airportList.forEach(airport -> {
            if (airport.getCity().equals(""))
                airport.setCity("unknown");
        });
    }

    public void createAircraftTypes() {
        Set<String> dataSet = readFile(pathAircraftType);

        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            for (int i = 0; i < stringArray.length; i++) {
                if (stringArray[i].contains(" ")) {
                    while (true) {
                        if (stringArray[i].charAt(stringArray[i].length() - 1) == ' ') {
                            stringArray[i] = stringArray[i].substring(0, (stringArray[i].length() - 1));
                        } else {
                            break;
                        }
                    }
                }
            }
            aircraftTypeSet.add(new AircraftType(stringArray[1], Integer.parseInt(stringArray[8])));
        });
    }

    public void createAircraftList() {
        List<AircraftType> aircraftTypeList = aircraftTypeSet.stream().collect(Collectors.toList());
        airlineList.forEach(airline -> {
            if (airline.getAirlineId() == (airlineList.size() / 2))
                return;
            aircraftList.add(new Aircraft(airline, aircraftTypeList.get(random.nextInt(aircraftTypeList.size()))));
        });
    }

    public void createFlights() {
        int counter = 0;
        for (Airline airline : airlineList) {
            if (counter == 2700)
                break;
            LocalTime departureTime = LocalTime.now().minus(random.nextInt(12), ChronoUnit.HOURS).minus(random.nextInt(59), ChronoUnit.MINUTES);
            LocalTime arrivalTime = LocalTime.now().plus(random.nextInt(12), ChronoUnit.HOURS).plus(random.nextInt(59), ChronoUnit.MINUTES);
            flights.add(new Flight(
                    aircraftList.get(random.nextInt(aircraftList.size())),
                    airline,
                    airportList.get(random.nextInt(airportList.size())),
                    departureTime,
                    airportList.get(random.nextInt(airportList.size())),
                    arrivalTime));
            counter++;
        }

        bidirectional();
    }

    public void bidirectional() {
        flights.forEach(flight -> {
            flight.getAircraft().getFlightList().add(flight);
            flight.getAirline().getFlights().add(flight);
            flight.getDepartureAirport().getDepartureFlights().add(flight);
            flight.getArrivalAirport().getArrivalFlights().add(flight);
            flight.getAircraft().getAirline().getAircraftList().add(flight.getAircraft());
            flight.getAircraft().getAirports().add(airportList.get(random.nextInt(airportList.size())));
            flight.getAircraft().getAirports().get(flight.getAircraft().getAirports().size() - 1).getAircraftList().add(flight.getAircraft());
        });
    }



}
