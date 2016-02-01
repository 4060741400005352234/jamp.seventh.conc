package jamp.seventh.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FolderStatistic {

    private volatile long folderCount = 0;
    private volatile long fileCount = 0;
    private volatile long totalFilesSize = 0;
    private Lock folderLock = new ReentrantLock();
    private Lock fileLock = new ReentrantLock();
    private Lock totalLock = new ReentrantLock();

    public synchronized void addFile() {
        //fileLock.lock();
        ++fileCount;
        //fileLock.unlock();
    }

    public synchronized void addFolder() {
        //folderLock.lock();
        ++folderCount;
        //folderLock.unlock();
    }

    public synchronized void addFileSize(long fileSize) {
        //totalLock.lock();
        totalFilesSize += fileSize;
        //totalLock.unlock();
    }

    public long getFolderCount() {
        return folderCount;
    }

    public long getFileCount() {
        return fileCount;
    }

    public long getTotalFilesSize() {
        return totalFilesSize;
    }
}
