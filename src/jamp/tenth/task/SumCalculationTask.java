package jamp.tenth.task;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class SumCalculationTask implements Runnable {

    private List<Integer> list;
    private Lock lock;

    public SumCalculationTask(List<Integer> list, Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            Long sum = 0L;
            for(Integer i : list) {
                sum += i;
            }
            System.out.println("Sum: " + sum);
            lock.unlock();
        }
    }
}
