package jamp.sixth.model;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    private Logger log = Logger.getLogger(Consumer.class);

    private String consumerName;
    private Semaphore semaphore;
    private Semaphore mutex;
    private Queue queue;

    public Consumer(String name, Semaphore semaphore, Semaphore mutex, Queue queue) {
        this.consumerName = name;
        this.semaphore = semaphore;
        this.mutex = mutex;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                semaphore.acquire();
                mutex.acquire();
                String result = queue.getData();
                System.out.println(consumerName + " consumes value: " + result + "\n");
                mutex.release();
            }
        } catch (Exception e) {
            log.error("Error occur.", e);
        }
    }
}

