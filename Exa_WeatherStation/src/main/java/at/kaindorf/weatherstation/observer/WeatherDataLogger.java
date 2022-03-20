package at.kaindorf.weatherstation.observer;

import at.kaindorf.weatherstation.beans.Weatherdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class WeatherDataLogger implements WeatherDataObserver{

    private static final DateTimeFormatter DTF = DateTimeFormatter.ISO_DATE_TIME;
    private FileWriter fw;
    private BufferedWriter bw;
    private static File dataFile = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weatherdata.csv").toFile();

    public WeatherDataLogger() {
        try {
            fw = new FileWriter(dataFile);
            bw = new BufferedWriter(fw);
            bw.write("timestamp;temperature;pressure;humidity;windSpeed;windDirection\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            fw.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Weatherdata weatherData) {
        try {
            bw.append(DTF.format(weatherData.getDateTime()) + ";" + weatherData.getTemperature() + ";" + weatherData.getPressure() + ";" + weatherData.getHumidity() + ";" + weatherData.getWindSpeed() + ";" + weatherData.getWindDirection()+ "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
