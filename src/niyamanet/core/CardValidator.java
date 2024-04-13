package niyamanet.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * "4111111111111111", // Visa
 * "5555555555554444", // Mastercard
 * "6011111111111117", // Discover
 * "378282246310005",  // Amex
 * "30569309025904",   // Diners Club
 * "3530111333300000", // JCB
 * "1234567890123456"  // Invalid
 */

public class CardValidator implements Validator {

    private File file;

    public CardValidator(File file) {
        this.file = file;
    }

    // Regular expression for card number extraction
    private static final String CARD_REGEX = "\\b(?:\\d[ -]*?){13,16}\\b";

    // Check if the card number is valid using Luhn algorithm
    public static boolean isValidCardNumber(String cardNumber) {
        // Remove non-digit characters
        String digitsOnly = cardNumber.replaceAll("\\D", "");

        // Luhn algorithm
        int sum = 0;
        boolean alternate = false;
        for (int i = digitsOnly.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(digitsOnly.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    // Extract card numbers from a given file
    public Map<String, Set<String>> analyseRecord() {
        Set<String> cardNumbers = new HashSet<>();
        Map<String, Set<String>> validCards = new HashMap<String, Set<String>>();

        Pattern pattern = Pattern.compile(CARD_REGEX);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String potentialCardNumber = matcher.group().replaceAll("[ -]", "");
                    if (isValidCardNumber(potentialCardNumber)) {
                        cardNumbers.add(potentialCardNumber);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
         }
        validCards.put("Cards", cardNumbers);
        return validCards;
    }

    public static void main(String[] args) {
        File file = new File("1_1.log");
        try {
            Map<String, Set<String>> cardGroup = new CardValidator(file).analyseRecord();
            System.out.println("Extracted card numbers:");
            for (String cardNumberG : cardGroup.keySet()) {
                Set<String> cards = cardGroup.get("Cards");
                for (String card : cards) {
                    System.out.println(card);
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
