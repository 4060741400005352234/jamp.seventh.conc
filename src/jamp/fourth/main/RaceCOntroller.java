package jamp.fourth.main;

import jamp.fourth.task.Car;

import java.util.concurrent.*;

public class RaceController {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FinishLine finishLine = new FinishLine();

        Car bmw = new Car("BMW", 100, latch, finishLine);
        Car mercedes = new Car("Mercedes", 100, latch, finishLine);
        Car audi = new Car("Audi", 100, latch, finishLine);
        Car mazda = new Car("Mazda", 100, latch, finishLine);
        Car honda = new Car("Honda", 100, latch, finishLine);

        executorService.submit(bmw);
        executorService.submit(mercedes);
        executorService.submit(audi);
        executorService.submit(mazda);
        executorService.submit(honda);

        Thread.sleep(1000);
        System.out.println("Start!");
        latch.countDown();

        Thread.sleep(5000);
        // Disqualifying
        mazda.stopCar();
        mercedes.stopCar();

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        System.out.println("Winner is " + finishLine.getWinner().getName());
    }
}
