package niyamanet.core.bin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Test read file");
        readThroughInputStream();
    }

    private static void readThroughInputStream() throws FileNotFoundException {
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            inputStream = new FileInputStream("src/findpattern/TestFileProcessing.properties");
            sc = new Scanner(inputStream, "UTF-8");
            System.out.println("Start time");
            long start6=System.currentTimeMillis();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }

            long end = System.currentTimeMillis();
            System.out.println("Total time taken : " +(start6 - end)/1000);

        } finally {
            /*
            if (inputStream != null) {
                inputStream.close();
            }
            */

            if (sc != null) {
                sc.close();
            }
        }

    }

    private void readFileWithScanner() throws IOException {
        String file = "src/findpattern/TestFileProcessing.properties";
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(" ");

        System.out.println("scanner.hasNext() : "+scanner.hasNext());
        scanner.close();
    }
    
}
