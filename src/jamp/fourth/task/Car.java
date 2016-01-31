package jamp.fourth.task;

import jamp.fourth.main.FinishLine;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {

    private Logger log = Logger.getLogger(getClass());

    private static final long MAX_DISTANCE = 10000;

    private long friction;
    private long distance;
    private volatile String name;
    private CountDownLatch latch;
    private FinishLine finishLine;
    private volatile boolean disqualified = true;

    public Car(String name, long friction, CountDownLatch latch, FinishLine finishLine) {
        this.name = name;
        this.friction = friction;
        this.latch = latch;
        this.finishLine = finishLine;
    }

    @Override
    public void run() {
        try {
            System.out.println("Car " + name + " is awaiting for race start.");
            latch.await();
            while (distance < MAX_DISTANCE && disqualified) {
                Thread.sleep(friction);
                distance += friction;
                log.info(name + " " + distance);
            }
            if (disqualified) {
                finishLine.arrive(this);
            } else {
                log.info(name + " is disqualified.");
            }

        } catch (InterruptedException e) {
            log.error(e);
        }
    }

    public void stopCar() {
        disqualified = false;
    }

    public String getName() {
        return name;
    }
}
