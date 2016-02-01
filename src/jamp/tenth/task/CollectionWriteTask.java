package jamp.tenth.task;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class CollectionWriteTask implements Runnable {

    private List<Integer> list;
    private Lock lock;
    private Random random = new Random();

    public CollectionWriteTask(List<Integer> list, Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            int nextInt = random.nextInt(10);
            list.add(nextInt);
            lock.unlock();
        }
    }
}
