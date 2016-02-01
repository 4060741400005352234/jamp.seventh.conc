package jamp.seventh.model;

public class FolderStatistic {

    private volatile long folderCount = 0;
    private volatile long fileCount = 0;
    private volatile long totalFilesSize = 0;

    public synchronized void addFile() {
        ++fileCount;
    }

    public synchronized void addFolder() {
        ++folderCount;
    }

    public synchronized void addFileSize(long fileSize) {
        totalFilesSize += fileSize;
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
