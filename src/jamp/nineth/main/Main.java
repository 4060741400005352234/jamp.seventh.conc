package jamp.nineth.main;

import jamp.nineth.task.SyncTask;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws Exception {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Lock lock3 = new ReentrantLock();

        Thread t1 = new Thread(new SyncTask(lock1, lock2), "T1");
        Thread t2 = new Thread(new SyncTask(lock2, lock3), "T2");
        Thread t3 = new Thread(new SyncTask(lock3, lock1), "T3");

        t1.start();
        Thread.sleep(3000);
        t2.start();
        Thread.sleep(3000);
        t3.start();

    }
}
