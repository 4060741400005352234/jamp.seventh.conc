package jamp.fourth.main;

import jamp.fourth.task.Car;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FinishLine {

    private Car winner;
    private Lock lock = new ReentrantLock();

    public void arrive(Car car) {
        lock.lock();
        if (winner == null) {
            winner = car;
        }
        lock.unlock();
    }

    public Car getWinner() {
        return winner;
    }
}
