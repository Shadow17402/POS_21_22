package at.kaindorf.pattern.comsumerProducer;

import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public class Consumer implements Runnable{

    private List<String> strings;

    public Consumer(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        //System.out.println("Consumer");
        while(!Thread.interrupted()) {
            synchronized (strings) {

            }
        }
    }
}
