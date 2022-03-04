package at.kaindorf.weatherstation.observer;

import at.kaindorf.weatherstation.beans.Weatherdata;

public class WeatherDataGUI implements WeatherDataObserver{

    private Weatherdata weatherdata;
    private WeatherDataSubject subject;

    public WeatherDataGUI(WeatherDataSubject subject) {
        this.subject = subject;
    }

    @Override
    public void update(Weatherdata weatherdata) {
        this.weatherdata = weatherdata;
    }

}
