package DB_Access;

import at.kaindorf.airlinereservationsystem.DB_Access;
import at.kaindorf.airlinereservationsystem.beans.Aircraft;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class DB_Access_Test {

    DB_Access dba;

    //Bevor es verwended wird muss "none" in der persistence.xml
    //statt "drop - and - create" eingegeben werden

    @Before
    public void testDB_Access(){
        dba = new DB_Access();
        dba.createEntityManager();
    }

    @Test
    public void test_Aircraft_getAllAircraftOfAirport(){
        List<Aircraft> expected = new ArrayList<>(); //muss selbst gemacht werden da DB random ist
        String airportName = "";
        Assertions.assertEquals(expected, dba.Aircraft_getAllAircraftOfAirport(airportName));
    }

    @Test
    public void test_Aircraft_getAllAircraftOfAirline(){
        List<Aircraft> expected = new ArrayList<>(); //muss selbst gemacht werden da DB random ist
        String airlineName = "";
        Assertions.assertEquals(expected, dba.Aircraft_getAllAircraftOfAirline(airlineName));
    }

    @Test
    public void test_Aircraft_getAllAircraftOfType(){
        List<Aircraft> expected = new ArrayList<>(); //muss selbst gemacht werden da DB random ist
        String typename = "";
        Assertions.assertEquals(expected, dba.Aircraft_getAllAircraftOfType(typename));
    }


}
