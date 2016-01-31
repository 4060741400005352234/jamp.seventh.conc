package jamp.third.main;

import jamp.third.task.Populator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; ++i) {
            executorService.execute(new Populator());
        }


        executorService.shutdown();

    }
}
