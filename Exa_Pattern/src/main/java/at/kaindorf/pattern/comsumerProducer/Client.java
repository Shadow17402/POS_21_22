package at.kaindorf.pattern.comsumerProducer;

import java.util.LinkedList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<String> strings = new LinkedList<>();
        //strings.add("abc");
        Producer producer = new Producer(strings);
        Consumer consumer = new Consumer(strings);

        Thread threadProd = new Thread(producer);
        Thread threadCons = new Thread(consumer);

        threadProd.start();
        threadCons.start();
    }

}
