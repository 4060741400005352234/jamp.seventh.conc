package jamp.seventh.task;

import jamp.seventh.model.FolderStatistic;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class FileIndexer2 implements Runnable {
    private Logger log = Logger.getLogger(getClass());

    private final ExecutorService executorService;
    private final FolderStatistic folderStatistic;
    private final File folder;
    private ArrayBlockingQueue<File> folders = new ArrayBlockingQueue<>(100);

    public FileIndexer2(FolderStatistic folderStatistic, File folder, ExecutorService executorService) {
        this.folderStatistic = folderStatistic;
        this.folder = folder;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            submitIndexingTask(folder);
            while (true) {
                File newFolder = folders.poll(10000, TimeUnit.MILLISECONDS);
                if (newFolder != null && newFolder.isDirectory()) {
                    submitIndexingTask(newFolder);
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            log.error("Failure.", e);
            Thread.currentThread().interrupt();
        }
    }

    private void submitIndexingTask(File folder) throws ExecutionException, InterruptedException {
        IndexingTask indexingTask = new IndexingTask(folder);
        executorService.submit(indexingTask);
    }

    private void registerNewFolder(File folder) {
        try {
            folders.put(folder);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class IndexingTask implements Runnable {

        private File folder;

        private IndexingTask(File folder) {
            this.folder = folder;
        }

        @Override
        public void run() {
            //System.out.println("Thread - " + Thread.currentThread().getName());
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread interrupted.");
            }
            File[] entries = folder.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        //System.out.println("New folder found - " + entry.getName());
                        folderStatistic.addFolder();
                        //submitIndexingTask(entry);
                        registerNewFolder(entry);
                    } else if (entry.isFile()) {
                        //System.out.println("New file found - " + entry.getName());
                        folderStatistic.addFile();
                        folderStatistic.addFileSize(entry.length());
                    }
                }
            }
        }
    }
}

