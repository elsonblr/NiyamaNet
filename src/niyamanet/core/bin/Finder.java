package niyamanet.core.bin;

import niyamanet.Interfaces.in.ConsoleServiceProperties;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Goal : Is to identify the sensitive information in a given directory, either logged by application / written inside a file
 */
public class Finder {

    public static void main(String[] args) {

      //  Reading the default directory when the java application runs/ or specify
        String sPath = null;
        for(String s : args) {
            sPath = args[0];
        }

        if(sPath == null) {
            Path currentRelativePath = Paths.get("");
            sPath = currentRelativePath.toAbsolutePath().toString();
        }
        System.out.println("sPath :"+sPath);

        // List all files in the directory, search for specific extensions
        File folder = new File(sPath);
        File[] list = folder.listFiles();

        System.out.println(" list length "+list.length);


        // TODO : it could be many files here - optimise to send required files
        for(int i = 0; i < list.length; i++) {
            // TODO add filtertion for file extensions
            ConsoleServiceProperties loadFile = new ConsoleServiceProperties();
            // load the properties, make a new class to verify the class & rules for every row.
            System.out.println(" file name "+list[i].getName());

            if(list[i].getName().endsWith(".log")) {
                // read file, using a simple method TODO : Optimised for speed and heavy data
                File file = new File(list[i].getName());
                try {
                    Scanner sc = new Scanner(file);
                    while(sc.hasNextLine()) {
                        System.out.println(" -- "+sc.nextLine() + " -- ");

                    }
                    // TODO : Plug the module here
                } catch(Exception e) {
                    e.printStackTrace();
                }
                System.out.println("File name : "+list[i].getName());
                System.out.println("Total scanned files");
            }
        }
    }

}