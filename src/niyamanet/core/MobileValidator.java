package niyamanet.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator class to identify & validate the mobile numbers
 * Pattern to be followed - EPP-style phone numbers use the format +CCC.NNNNNNNNNNxEEEE, where C is the 1–3 digit country code, N is up to 14 digits, and E is the (optional) extension. The leading plus sign and the dot following the country code are required. The literal “x” character is required only if an extension is provided.
 */
public class MobileValidator implements Validator {

    private static final Map<String, String> countryRegexMap = new HashMap<>();
    private File file;

    public MobileValidator(File file) {
        this.file = file;
    }

    static {
        // TODO : Country regex map should be loaded for different countries
        // Regular expressions for mobile number formats for different countries
        countryRegexMap.put("USA", "\\b\\(?\\d{3}\\)?[-. ]?\\d{3}[-. ]?\\d{4}\\b");
        countryRegexMap.put("UK", "\\b\\(?0\\d{4}\\)?[-. ]?\\d{6}\\b");
        countryRegexMap.put("India", "\\b\\(?\\d{5}\\)?[-. ]?\\d{5}\\b");
        // Add more countries and corresponding regular expressions as needed
    }


    public static void main(String[] args) {

        System.out.println("New mobile validator version");
        File file = new File("1_1.log");
        Map<String, Set<String>> countryMobileNumbers = new MobileValidator(file).analyseRecord();

        // Display mobile numbers for each country
        for (String country : countryMobileNumbers.keySet()) {
            System.out.println("Mobile Numbers for " + country + ":");
            Set<String> mobileNumbers = countryMobileNumbers.get(country);
            for (String number : mobileNumbers) {
                System.out.println(number);
            }
            System.out.println();
        }
    }

    public Map<String, Set<String>> analyseRecord() {
        Map<String, Set<String>> countryMobileNumbers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String country : countryRegexMap.keySet()) {
                    String regex = countryRegexMap.get(country);
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        if (!countryMobileNumbers.containsKey(country)) {
                            countryMobileNumbers.put(country, new HashSet<>());
                        }
                        countryMobileNumbers.get(country).add(matcher.group());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryMobileNumbers;
    }

}
