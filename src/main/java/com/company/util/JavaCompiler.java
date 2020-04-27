package com.company.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JavaCompiler {

    public static String compileAndRun(String file) {
        String result = "";
        String fileName = file.substring(file.lastIndexOf("\\") + 1);
        String folderPath = file.replace(fileName, "");

        fileName = fileName.replace(".java", "");
        try {
            String[] compile = {"javac", file};
            String[] run = {"java", "-cp", folderPath, fileName};

            // Compile
            var compileProcess = Runtime.getRuntime().exec(compile);
            int fc = compileProcess.getErrorStream().read();
            if( fc != -1 ) {
                System.out.print((char) fc);
                String data = getOutput(compileProcess.getErrorStream());
                System.out.println(data);
                result += data;
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
                    result += data;
                } else {
                    String data = getOutput(runProcess.getInputStream());
                    System.out.println(data);
                    result += data;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            result += e;
        }
        return result;
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
