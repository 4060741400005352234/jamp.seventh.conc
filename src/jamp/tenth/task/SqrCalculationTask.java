package jamp.tenth.task;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class SqrCalculationTask implements Runnable {

    private List<Integer> list;
    private Lock lock;

    public SqrCalculationTask(List<Integer> list, Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            Long squareSum = 0L;
            for(Integer i : list) {
                squareSum += i*i;
            }
            System.out.println("Square root  of sum of squares: " + squareSum*squareSum);
            lock.unlock();
        }

    }
}
