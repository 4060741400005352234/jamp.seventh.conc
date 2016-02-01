package jamp.seventh.task;

import jamp.seventh.model.FolderStatistic;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.concurrent.*;

public class FileIndexer implements Runnable {

    private Logger log = Logger.getLogger(getClass());

    private final ExecutorService executorService;
    private final FolderStatistic folderStatistic;
    private final File folder;
    private volatile boolean isInitialThread = true;

    public FileIndexer(FolderStatistic folderStatistic, File folder, ExecutorService executorService) {
        this.folderStatistic = folderStatistic;
        this.folder = folder;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            submitIndexingTask(folder);
        } catch (Exception e) {
            log.error("Failure.");
            Thread.currentThread().interrupt();
        }
    }

    private void submitIndexingTask(File folder) throws ExecutionException, InterruptedException {
        IndexingTask indexingTask = new IndexingTask(folder);
        Future<Boolean> result = executorService.submit(indexingTask);
        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Task execution problem detected.", e);
            throw e;
        }
    }

    private class IndexingTask implements Callable<Boolean> {

        private File folder;

        private IndexingTask(File folder) {
            this.folder = folder;
        }

        @Override
        public Boolean call() throws ExecutionException, InterruptedException {
            //System.out.println("Thread - " + Thread.currentThread().getName());
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread interrupted.");
                return false;
            }
            File[] entries = folder.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        //System.out.println("New folder found - " + entry.getName());
                        folderStatistic.addFolder();
                        submitIndexingTask(entry);
                    } else if (entry.isFile()) {
                        //System.out.println("New file found - " + entry.getName());
                        folderStatistic.addFile();
                        folderStatistic.addFileSize(entry.length());
                    }
                }
            }
            return true;
        }
    }
}
