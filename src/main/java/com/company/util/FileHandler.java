package com.company.util;

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
    public String open() {
        String content = null;
        try {
            content = Files.readString(Paths.get(filePath));

        } catch(IOException e) {
            System.out.println("problem with IO");
            e.printStackTrace();
        }
        return content;
    }

    // Saves the content to given file path
    public void save(String content) {
        try {
            Files.writeString(Paths.get(filePath), content);
        } catch(IOException e) {
            System.out.println("problem with IO");
            e.printStackTrace();
        }
    }
}
