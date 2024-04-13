package niyamanet.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {

    private File file;

    public EmailValidator(File file) {
        this.file = file;
    }


    public static void main(String[] args) {
        File file = new File("1_1.log");
        Map<String, Set<String>> emailsGrouped = new EmailValidator(file).analyseRecord();
        for (String emailG : emailsGrouped.keySet()) {
            System.out.println("Extracted Emails:");
            Set<String> emails = emailsGrouped.get("Email");
            for (String email : emails) {
                System.out.println(email);
            }
        }
    }

    public  Map<String, Set<String>> analyseRecord() {
        Set<String> emails = new HashSet<>();
        Map<String, Set<String>> validEmails = new HashMap<String, Set<String>>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                emails.addAll(extractEmailsFromLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        validEmails.put("Email", emails);
        return validEmails;
    }

    public static Set<String> extractEmailsFromLine(String line) {
        Set<String> emails = new HashSet<>();
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            emails.add(matcher.group());
        }
        return emails;
    }

}
