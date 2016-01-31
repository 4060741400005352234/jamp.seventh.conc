package jamp.sixth.main;

import jamp.sixth.model.Queue;
import jamp.sixth.model.Consumer;
import jamp.sixth.model.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ProducerConsumerTest {

    public void start() {
        Semaphore semaphore = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
        Queue queue = new Queue();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Producer("Producer_1", semaphore, mutex, queue));
        executorService.submit(new Producer("Producer_2", semaphore, mutex, queue));
        executorService.submit(new Consumer("Listener_1", semaphore, mutex, queue));
        executorService.submit(new Consumer("Listener_2", semaphore, mutex, queue));
        executorService.submit(new Consumer("Listener_3", semaphore, mutex, queue));

        executorService.shutdown();
    }
}
