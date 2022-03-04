package at.kaindorf.weatherstation.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class WeatherDataSubject {

    protected List<WeatherDataObserver> obervers = new ArrayList<>();

    public void register(WeatherDataObserver observer){
        obervers.add(observer);
    }

    public void unregister(WeatherDataObserver observer){
        obervers.remove(observer);
    }

    public abstract void notifyObservers();

}
