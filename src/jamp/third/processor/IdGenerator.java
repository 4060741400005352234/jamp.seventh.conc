package jamp.third.processor;

public enum IdGenerator {

    INSTANCE;

    private volatile long lastId;

    public long getNextId() {
        synchronized (this) {
            return lastId += 1;
        }
    }
}
