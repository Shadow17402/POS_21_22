package at.kaindorf.weatherstation.observer;

import at.kaindorf.weatherstation.beans.Weatherdata;

public class WeatherDataPrinter implements WeatherDataObserver{

    @Override
    public void update(Weatherdata weatherdata) {
        System.out.println(weatherdata);
    }

}
