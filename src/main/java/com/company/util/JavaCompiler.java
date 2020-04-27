package com.company.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JavaCompiler {

    public static String compileAndRun(String file) {
        String result = "";
        String fileName = file.substring(file.lastIndexOf("\\") + 1);
        System.out.println(fileName);
        String folderPath = file.replace(fileName, "");
        System.out.println(folderPath);
        fileName = fileName.replace(".java", "");
        try {
            String[] compile = {"javac", file};
            String[] run = {"java", "-cp", folderPath, fileName};

            var compileProcess = Runtime.getRuntime().exec(compile);
            int fc = compileProcess.getErrorStream().read();
            if( fc != -1 ) {
                System.out.print((char) fc);
                //String data = getOutput(compileProcess.getErrorStream());
                //System.out.println(data);
                //result += data;
            }

            int value = compileProcess.waitFor();
            if(value == 0) {
                var runProcess = Runtime.getRuntime().exec(run);
                int fcs = compileProcess.getErrorStream().read();
                if(fcs != -1) {
                    System.out.print((char) fcs);
                    //print(runProcess.getErrorStream());
                } else {
                    //print(runProcess.getInputStream());
                    InputStream inputStream = runProcess.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                        result = result + line;
                        System.out.println(result);
                    }
                    bufferedReader.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
