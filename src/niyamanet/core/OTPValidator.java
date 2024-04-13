package niyamanet.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPValidator implements Validator {

    private File file;

    public OTPValidator(File file) {
        this.file = file;
    }


    public static void main(String[] args) {
        File file = new File("1_1.log");

        Map<String,Set<String>> otpGroup = new OTPValidator(file).analyseRecord();
        for(String otpG : otpGroup.keySet()) {
            System.out.println("Extracted OTPs:");
            Set<String> otps = otpGroup.get("OTP");
            for (String otp : otps) {
                System.out.println(otp);
            }
        }

    }

    public Map<String, Set<String>> analyseRecord () {
        Set<String> otps = new HashSet<>();
        BufferedReader reader = null;
        Map<String, Set<String>> otpExtract = new HashMap<String, Set<String>>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            // Regular expression for extracting OTPs (assuming 6-digit numeric OTPs)
            Pattern pattern = Pattern.compile("\\b\\d{6}\\b");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    otps.add(matcher.group());
                }
            }
            otpExtract.put("OTP", otps);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return otpExtract;
    }

}
