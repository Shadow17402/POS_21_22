package at.kaindorf.pattern.comsumerProducer;

import java.util.List;

public class Producer implements Runnable{

    private List<String> strings;
    private static final String[] values = {"a","b","c"};

    public Producer(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        //System.out.println("Producer");
        while(!Thread.interrupted()){
            synchronized (strings){
                strings.add("LUL");
            }
        }
    }
}
