package jamp.nineth.task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncTask implements Runnable {

    private final Lock lock1;
    private final Lock lock2;

    public SyncTask(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if (lock1.tryLock()) {
            try {
                System.out.println(name + " acquired lock on Lock_1: " + lock1);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (lock2.tryLock()) {
                    try {
                        System.out.println(name + " acquired lock on Lock_2: " + lock2);
                    } finally {
                        System.out.println(name + " released lock on Lock_2: " + lock2);
                    }
                }
            } finally {
                System.out.println(name + " released lock on Lock_1: " + lock2);
                lock1.unlock();
            }
        }
        System.out.println(name + " Finished.");

    }
}
