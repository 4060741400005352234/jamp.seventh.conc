package jamp.seventh.task;

import jamp.seventh.model.FolderStatistic;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileIndexer implements Runnable {

    private final ExecutorService exec = Executors.newFixedThreadPool(10);
    private final FolderStatistic folderStatistic;
    private final File folder;

    public FileIndexer(FolderStatistic folderStatistic, File folder) {
        this.folderStatistic = folderStatistic;
        this.folder = folder;
    }

    @Override
    public void run() {
        submitIndexingTask(folder);
    }

    private void submitIndexingTask(File folder) {
        IndexingTask indexingTask = new IndexingTask(folder);
        exec.execute(indexingTask);
    }

    private class IndexingTask implements Runnable {

        private File folder;

        private IndexingTask(File folder) {
            this.folder = folder;
        }

        @Override
        public void run() {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            File[] entries = folder.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        System.out.println("New folder found - " + entry.getName());
                        folderStatistic.addFolder();
                        submitIndexingTask(entry);
                    } else if (entry.isFile()) {
                        System.out.println("New file found - " + entry.getName());
                        folderStatistic.addFile();
                        folderStatistic.addFileSize(entry.length());
                    }
                }
            }
        }
    }
}
