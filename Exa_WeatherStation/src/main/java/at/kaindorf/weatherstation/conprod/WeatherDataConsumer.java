package at.kaindorf.weatherstation.conprod;

import at.kaindorf.weatherstation.beans.Weatherdata;
import at.kaindorf.weatherstation.observer.WeatherDataSubject;

import java.util.Queue;

public class WeatherDataConsumer extends WeatherDataSubject implements Runnable{

    private Queue<Weatherdata> dataQueue;
    private Weatherdata weatherdata;

    public WeatherDataConsumer(Queue<Weatherdata> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (dataQueue) {
                weatherdata = dataQueue.poll();
                if (weatherdata != null) {
                    notifyObservers();
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(weatherdata));
    }

}
