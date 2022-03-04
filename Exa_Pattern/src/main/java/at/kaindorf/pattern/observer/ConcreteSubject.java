package at.kaindorf.pattern.observer;

public class ConcreteSubject extends Subject{

    private String data = "hello";

    @Override
    public void notifyObservers() {
        for(Observer observer:obervers){
            observer.update(data);
        }
    }

}
