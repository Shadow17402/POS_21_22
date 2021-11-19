package at.kaindorf.airlinereservationsystem;

import at.kaindorf.airlinereservationsystem.Data_Import;
import at.kaindorf.airlinereservationsystem.beans.Aircraft;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DB_Access {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static Data_Import data = new Data_Import();

    public static void createData(){
        data.createAircraftTypes();
        data.createAirlines();
        data.createAirports();
        data.createAircraftList();
        data.createFlights();

        System.out.println(data.getFlights().size() + " Flights were created");

        data.getFlights().forEach(f -> {
                em.persist(f);
        });
    }

    public List<Aircraft> Aircraft_getAllAircraftOfType(String name){
        TypedQuery<Aircraft> typedQuery = em.createNamedQuery("Aircraft.getAllAircraftOfType", Aircraft.class);
        typedQuery.setParameter("typename", name);
        List<Aircraft> aircrafts = typedQuery.getResultList();
        System.out.println("AllAircraftOfType: ");
        aircrafts.forEach(System.out::println);
        System.out.println("\n");
        return aircrafts;
    }

    public List<Aircraft> Aircraft_getAllAircraftOfAirport(String name){
        TypedQuery<Aircraft> typedQuery = em.createNamedQuery("Aircraft.getAllAircraftOfAirport", Aircraft.class);
        typedQuery.setParameter("airport", name);
        List<Aircraft> aircrafts = typedQuery.getResultList();
        System.out.println("AllAircraftOfAirport: ");
        aircrafts.forEach(System.out::println);
        System.out.println("\n");
        return aircrafts;
    }

    public List<Aircraft> Aircraft_getAllAircraftOfAirline(String name){
        TypedQuery<Aircraft> typedQuery = em.createNamedQuery("Aircraft.getAllAircraftOfAirline", Aircraft.class);
        typedQuery.setParameter("airlineName", name);
        List<Aircraft> aircrafts = typedQuery.getResultList();
        System.out.println("AllAircraftOfAirline: ");
        aircrafts.forEach(System.out::println);
        System.out.println("\n");
        return aircrafts;
    }

    //damit der em auch von der Testklasse erstellt werden kann, da sonst eine neue DB erstellt wird
    public void createEntityManager(){
        emf = Persistence.createEntityManagerFactory("PU_Airline_DB");
        em = emf.createEntityManager();
    }

    public static void main(String[] args) {
        DB_Access dba = new DB_Access();
        dba.createEntityManager();
        em.getTransaction().begin();
        createData();
        em.getTransaction().commit();
    }

}
