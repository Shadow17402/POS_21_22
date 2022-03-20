package at.kaindorf.weatherstation.conprod;

import at.kaindorf.weatherstation.beans.Weatherdata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class WeatherDataProducer implements Runnable{

    public static final Path PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weatherdata.json");
    private Queue<Weatherdata> dataQueue;

    public WeatherDataProducer(Queue<Weatherdata> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        List<Weatherdata> dataList = new ArrayList<>();
        try {
            dataList = new ObjectMapper().readValue(PATH.toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        long period;
        for (int i = 0; i < dataList.size(); i++) {
            synchronized (dataQueue) {
                if (i != dataList.size()-1) {
                    period = ChronoUnit.SECONDS.between(dataList.get(i).getDateTime(), dataList.get(i + 1).getDateTime()) * 1000;
                } else {
                    period = 0;
                }
                dataQueue.add(dataList.get(i));
            }
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
