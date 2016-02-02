package jamp.fifth.model;

import org.apache.log4j.Logger;

public class Counter {

    private Logger log = Logger.getLogger(Counter.class);

    private volatile int count = 10;

    public synchronized void increment() {
        count++;
        notify();
    }

    public synchronized void decrement() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("Thread interrupted.", e);
            }
        }
        count--;
    }

    public int get() {
        return count;
    }
}
