package jamp.tenth.main;

import jamp.tenth.task.CollectionWriteTask;
import jamp.tenth.task.SumCalculationTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        List<Integer> list = new ArrayList<>();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        exec.execute(new CollectionWriteTask(list, writeLock));
        exec.execute(new SumCalculationTask(list, readLock));
        exec.execute(new SquareCalculationTask(list, readLock));

        exec.shutdown();
    }
}
