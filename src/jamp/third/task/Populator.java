package jamp.third.task;

import jamp.third.processor.IdGenerator;

public class Populator implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(100);
                System.out.println(IdGenerator.INSTANCE.getNextId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
