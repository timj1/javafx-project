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
        try {
            String[] compileRun = {"java", "-cp", folderPath, fileName};

            var compileRunProcess = Runtime.getRuntime().exec(compileRun);

            InputStream inputStream = compileRunProcess.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println(bufferedReader.readLine());
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                result = result + line;
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
