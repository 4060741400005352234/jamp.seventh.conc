package jamp.sixth.model;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

    private Logger log = Logger.getLogger(Producer.class);

    private String name;
    private Semaphore semaphore;
    private Semaphore mutex;
    private Queue queue;

    public Producer(String name, Semaphore semaphore, Semaphore mutex, Queue queue) {
        this.name = name;
        this.semaphore = semaphore;
        this.mutex = mutex;
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 1;
        try {
            while (true) {
                String threadName = Thread.currentThread().getName() + counter++;
                mutex.acquire();
                queue.setData(threadName);
                System.out.println(name + " is producing new value: " + threadName);
                mutex.release();
                semaphore.release();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            log.error("Error occur.", e);
        }
    }
}
