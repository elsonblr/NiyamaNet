package niyamanet.Interfaces.in;

import niyamanet.core.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

public class ConsoleService {

//    private static void init() {
//        ConsoleServiceProperties loadFile = new ConsoleServiceProperties();
//    }

    private static String getDirectoryPath(String[] args) {
        // Reading the default directory when the java application runs/ or specify
        String sPath = null;
        for(String s : args) {
            sPath = args[0];
        }

        if(sPath == null) {
            Path currentRelativePath = Paths.get("");
            sPath = currentRelativePath.toAbsolutePath().toString();
        }
        return sPath;
    }

    public static void main(String[] args) {
        // This class should have a config file
        // configuration in terms to enabling/disabling rules
        // ex : mobilevalidation = true, card vlidation = true

        // Reading the default directory when the java application runs/ or specify
        String sPath = getDirectoryPath(args);
        // TODO : handle exception senarios, path does not exsist come out

        //init();
        // TODO : Any exception in inialisation should come out

        // List all files in the directory, search for specific extensions
        File folder = new File(sPath);
        File[] list = folder.listFiles();
        System.out.println(" Total files in directory " + sPath + " is " +list.length);

        ConsoleServiceProperties loadFile = new ConsoleServiceProperties();

        // TODO : it could be many files here - optimise to send required files
        for(int i = 0; i < list.length; i++) {
            // TODO add filtertion for file extensions
            //System.out.println(" file name "+list[i].getName());
            if(list[i].getName().endsWith(loadFile.getFileExtension())) {
                System.out.println(" Processing file " + list[i].getName());
                File file = new File(list[i].getName());
                Validator mobileValidator = new MobileValidator(file);
                Validator emailValidator = new EmailValidator(file);
                Validator otpValidator = new OTPValidator(file);
                Validator cardValidator = new CardValidator(file);

                ValidationInvoker invoker = new ValidationInvoker();
                Map<String, Set<String>> validMobiles = invoker.executeCommand(mobileValidator);
                Map<String, Set<String>> validEmails = invoker.executeCommand(emailValidator);
                Map<String, Set<String>> validOtps = invoker.executeCommand(otpValidator);
                Map<String, Set<String>> validCards = invoker.executeCommand(cardValidator);

                for (String country : validMobiles.keySet()) {
                    System.out.println("Mobile Numbers for " + country + ":");
                    Set<String> mobileNumbers = validMobiles.get(country);
                    for (String number : mobileNumbers) {
                        System.out.println(number);
                    }
                    System.out.println();
                }

                for (String emailGroup : validEmails.keySet()) {
                    System.out.println("Extracted Emails:");
                    Set<String> emails = validEmails.get("Email");
                    for (String email : emails) {
                        System.out.println(email);
                    }
                }

                for (String otpGroup : validOtps.keySet()) {
                    System.out.println("Extracted OTPs:");
                    Set<String> emails = validOtps.get("OTP");
                    for (String email : emails) {
                        System.out.println(email);
                    }
                }

                for (String cardGroup : validCards.keySet()) {
                    System.out.println("Extracted cards:");
                    Set<String> cards = validCards.get("Cards");
                    for (String card : cards) {
                        System.out.println(card);
                    }
                }

            }
        }
    }

}