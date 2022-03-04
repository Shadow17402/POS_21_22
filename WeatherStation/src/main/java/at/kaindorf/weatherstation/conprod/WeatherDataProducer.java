package at.kaindorf.weatherstation.conprod;

import at.kaindorf.weatherstation.beans.Weatherdata;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WeatherDataProducer implements Runnable{

    private Path JSONpath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weatherdata.json");

    @Override
    public void run() {

    }

    public void readJSON(){
        ObjectMapper mapper = new ObjectMapper();
        List<Weatherdata> weatherdataList = null;
        try {
            weatherdataList = Arrays.asList(mapper.readValue(JSONpath.toFile(), Weatherdata[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        weatherdataList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        WeatherDataProducer p = new WeatherDataProducer();
        p.readJSON();
    }

}
