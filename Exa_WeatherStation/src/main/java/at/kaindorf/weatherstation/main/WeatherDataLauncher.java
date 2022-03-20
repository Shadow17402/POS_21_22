package at.kaindorf.weatherstation.main;

import at.kaindorf.weatherstation.beans.Weatherdata;
import at.kaindorf.weatherstation.conprod.WeatherDataConsumer;
import at.kaindorf.weatherstation.conprod.WeatherDataProducer;
import at.kaindorf.weatherstation.observer.WeatherDataGUI;
import at.kaindorf.weatherstation.observer.WeatherDataLogger;
import at.kaindorf.weatherstation.observer.WeatherDataPrinter;

import java.util.LinkedList;
import java.util.Queue;

public class WeatherDataLauncher {

    public static void main(String[] args) {
        Queue<Weatherdata> dataQueue = new LinkedList<>();
        WeatherDataProducer weatherDataProducer = new WeatherDataProducer(dataQueue);
        WeatherDataConsumer weatherDataConsumer = new WeatherDataConsumer(dataQueue);

        WeatherDataLogger weatherDataLogger = new WeatherDataLogger();
        WeatherDataPrinter weatherDataPrinter = new WeatherDataPrinter();
        WeatherDataGUI weatherDataGUI = new WeatherDataGUI();

        weatherDataConsumer.register(weatherDataLogger);
        weatherDataConsumer.register(weatherDataPrinter);
        weatherDataConsumer.register(weatherDataGUI);

        Thread threadC = new Thread(weatherDataConsumer);
        threadC.start();
        Thread threadP = new Thread(weatherDataProducer);
        threadP.start();
    }

}
