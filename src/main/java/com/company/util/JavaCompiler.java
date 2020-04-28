package com.company.util;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JavaCompiler {
    private static String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static void compileAndRun(CallbackCompile callback) {
        // Java file
        String fileName = path.substring(path.lastIndexOf("\\") + 1).replace(".java", "");
        String folderPath = path.replace(fileName + ".java", "");

        String[] compile = {"javac", path};
        String[] run = {"java", "-cp", folderPath, fileName};

        Thread t = new Thread(() -> {
        try {

            // Compile
            var compileProcess = Runtime.getRuntime().exec(compile);
            int fc = compileProcess.getErrorStream().read();
            if( fc != -1 ) {
                System.out.print((char) fc);
                String data = getOutput(compileProcess.getErrorStream());
                System.out.println(data);
                //result += data;
                Platform.runLater(() -> callback.received(data));
            }

            int value = compileProcess.waitFor();

            // Run
            if(value == 0) {
                var runProcess = Runtime.getRuntime().exec(run);
                int fcs = compileProcess.getErrorStream().read();
                if(fcs != -1) {
                    System.out.print((char) fcs);
                    String data = getOutput(runProcess.getErrorStream());
                    System.out.println(data);
                    //result += data;
                    Platform.runLater(() -> callback.received(data));
                } else {
                    String data = getOutput(runProcess.getInputStream());
                    System.out.println(data);
                    //result += data;
                    Platform.runLater(() -> callback.received(data));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            //result += e;
            Platform.runLater(() -> callback.received(e.toString()));
        }
        //return result;
        });
        t.start();
    }
    public interface CallbackCompile {
        public void received(String content);
    }
    public static String getOutput(InputStream stream) throws IOException {
        String result = "";
        try(BufferedReader bufferedReader
                    = new BufferedReader(new InputStreamReader(stream))) {
            result = bufferedReader.lines().collect(Collectors.joining("\n"));
        }
        return result;
    }
}
