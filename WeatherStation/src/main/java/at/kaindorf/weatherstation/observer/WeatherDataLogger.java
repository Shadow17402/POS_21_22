package at.kaindorf.weatherstation.observer;

import at.kaindorf.weatherstation.beans.Weatherdata;

public class WeatherDataLogger implements WeatherDataObserver{

    private Weatherdata weatherdata;
    private WeatherDataSubject subject;

    public WeatherDataLogger(WeatherDataSubject subject) {
        this.subject = subject;
    }

    @Override
    public void update(Weatherdata weatherdata) {
        this.weatherdata = weatherdata;
    }

}
