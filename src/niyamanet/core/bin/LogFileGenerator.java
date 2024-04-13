package niyamanet.core.bin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileGenerator {

    public static void main(String[] args) {
        String fileName = "logfile01.log"; // Name of the log file
        long fileSizeInBytes = 10 * 1024 * 1024; // 10 MB in bytes
        int lineLength = 100; // Length of each line in characters

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            long bytesWritten = 0;
            StringBuilder lineBuilder = new StringBuilder();
            while (bytesWritten < fileSizeInBytes) {
                // Generate a random line of characters
                for (int i = 0; i < lineLength; i++) {
                    char randomChar = (char) ('A' + Math.random() * ('z' - 'A' + 1));
                    lineBuilder.append(randomChar);
                }
                // Append newline character
                lineBuilder.append(System.lineSeparator());

                // Write the line to the file
                writer.write(lineBuilder.toString());
                bytesWritten += lineBuilder.length();
                // Clear the StringBuilder for the next line
                lineBuilder.setLength(0);
            }
            System.out.println("Log file generated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
