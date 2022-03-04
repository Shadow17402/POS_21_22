package at.kaindorf.weatherstation.observer;

import at.kaindorf.weatherstation.beans.Weatherdata;

public interface WeatherDataObserver {
    void update(Weatherdata weatherdata);
}
