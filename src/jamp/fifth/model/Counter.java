package jamp.fifth.model;

public class Counter {

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
                e.printStackTrace();
            }
        }
        count--;
    }

    public int get() {
        return count;
    }
}
