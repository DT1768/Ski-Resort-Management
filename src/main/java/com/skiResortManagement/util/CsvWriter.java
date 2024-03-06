package com.skiResortManagement.util;

import com.skiResortManagement.model.ServerResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Queue;

public class CsvWriter {

    public static void writeQueueToCsv(Queue<ServerResponse> queue, String fileName, boolean append) {
        // Check if the file already exists; if not, append is set to false to write the header
        boolean fileExists = Files.exists(Paths.get(fileName));
        try (FileWriter writer = new FileWriter(fileName, fileExists && append)) {
            // Write the CSV header if the file does not exist or if we are not appending
            if (!fileExists || !append) {
                writer.append("Start Time,End Time,Status Code,Response,Latency\n");
            }

            for (ServerResponse entry : queue) {
                writer.append(String.valueOf(entry.getStartTime()))
                        .append(',')
                        .append(String.valueOf(entry.getEndTime()))
                        .append(',')
                        .append(entry.getStatusCode())
                        .append(',')
                        .append(entry.getResponse().replace(",", ";")) // Assuming response may contain commas, replace them to keep CSV format
                        .append(',')
                        .append(String.valueOf(entry.getLatency()))
                        .append('\n');
            }

            System.out.println("CSV file " + fileName + " was updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing/updating the CSV file: " + fileName);
            e.printStackTrace();
        }
    }
}

