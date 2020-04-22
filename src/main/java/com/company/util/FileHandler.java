package com.company.util;

import javafx.application.Platform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    private String filePath;

    public FileHandler(String filePath) {
        setFilePath(filePath);
    }

    public FileHandler() {}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Opens the given file and returns the content, uses filePath
    public void open(CallbackOpen callback) {
        //String content = null;
        Thread t = new Thread(() -> {
        try {
            Thread.sleep(10000);
            String content = Files.readString(Paths.get(filePath));
            Platform.runLater(() -> callback.received(content));
        } catch(IOException e) {
            System.out.println("problem with IO");
            e.printStackTrace();
        } catch (Exception e) {}
        //return content;
        });
        t.start();
    }

    public interface CallbackOpen {
        public void received(String content);
    }

    // Saves the content to given file path
    public void save(CallbackSave callback, String content) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
                Files.writeString(Paths.get(filePath), content);
                Platform.runLater(() -> callback.received(false));
            } catch(IOException e) {
                System.out.println("problem with IO");
                e.printStackTrace();
            } catch (Exception e){}
        });
        t.start();
    }
    public interface CallbackSave {
        public void received(boolean b);
    }

}
