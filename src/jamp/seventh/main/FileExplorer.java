package jamp.seventh.main;

import jamp.seventh.model.FolderStatistic;
import jamp.seventh.task.FileIndexer;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileExplorer {

    public static void main(String[] args) throws InterruptedException {
        File folder = new File("D:\\IBM");

        FileExplorer fileExplorer = new FileExplorer();
        fileExplorer.startScan(folder);
    }

    public void startScan(File folder) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        FolderStatistic folderStatistic = new FolderStatistic();

        Thread folderIndexer = new Thread(new FileIndexer(folderStatistic, folder, executorService));
        folderIndexer.start();

        folderIndexer.join();

        System.out.println("Finish");
        System.out.println("Files: " + folderStatistic.getFileCount());
        System.out.println("Folders: " + folderStatistic.getFolderCount());
        System.out.println("Total files size: " + folderStatistic.getTotalFilesSize() + " bytes");

        executorService.shutdown();
    }
}
