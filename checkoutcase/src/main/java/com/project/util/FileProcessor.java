package com.project.util;

import com.project.dto.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public static List<String> readRequestLinesFromFile(String filename) throws IOException {
        List<String> requestLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            requestLines.add(line);
        }
        reader.close();
        return requestLines;
    }

    public static void writeResponsesToFile(List<Response> responses, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Response response : responses) {
            writer.write(response.toString());
            writer.newLine();
        }
        writer.close();
    }
}
