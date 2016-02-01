package jamp.seventh.main;

import jamp.seventh.model.FolderStatistic;
import jamp.seventh.task.FileIndexer;

import java.io.File;

public class FileExplorer {

    public static void main(String[] args) throws InterruptedException {
        File folder = new File("D:\\Java\\JAMP\\folder");

        FileExplorer fileExplorer = new FileExplorer();
        fileExplorer.startScan(folder);
    }

    public void startScan(File folder) throws InterruptedException {
        FolderStatistic folderStatistic = new FolderStatistic();

        Thread folderIndexer = new Thread(new FileIndexer(folderStatistic, folder));
        folderIndexer.start();

        Thread.sleep(10000);
        System.out.println("Finish");
        System.out.println("Files: " + folderStatistic.getFileCount());
        System.out.println("Folders: " + folderStatistic.getFolderCount());
        System.out.println("Total file size: " + folderStatistic.getTotalFilesSize());
    }
}
