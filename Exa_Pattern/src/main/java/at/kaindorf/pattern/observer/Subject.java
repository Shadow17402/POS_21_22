package at.kaindorf.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    protected List<Observer> obervers = new ArrayList<>();

    public void register(Observer observer){
            obervers.add(observer);
    }

    public void unregister(Observer observer){
        obervers.remove(observer);
    }

    public abstract void notifyObservers();

}
