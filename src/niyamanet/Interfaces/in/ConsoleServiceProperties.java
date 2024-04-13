package niyamanet.Interfaces.in;

import java.io.InputStream;
import java.util.Properties;

public class ConsoleServiceProperties {
    String searchFrequency;
    String logRequired;
    String fileExtension;
    String mobileValidationRequired;
    String mailValidationRequired;
    String cardValidationRequired;
    String otpValidationRequired;

    public ConsoleServiceProperties() {
        Properties prop = new Properties();
        InputStream stream = getClass().getResourceAsStream("console.properties");
        try {
            prop.load(stream);
            setFileExtension(prop.getProperty("file.extension"));
            setCardValidationRequired(prop.getProperty("card.validation.required"));
            setLogRequired(prop.getProperty("log.required"));
            setMailValidationRequired(prop.getProperty("mail.validation.required"));
            setMobileValidationRequired(prop.getProperty("mobile.validation.required"));
            setOtpValidationRequired(prop.getProperty("otp.validation.required"));
            setSearchFrequency(prop.getProperty("search.frequency"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public String getSearchFrequency() {
        return searchFrequency;
    }

    public void setSearchFrequency(String searchFrequency) {
        this.searchFrequency = searchFrequency;
    }

    public String getLogRequired() {
        return logRequired;
    }

    public void setLogRequired(String logRequired) {
        this.logRequired = logRequired;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getMobileValidationRequired() {
        return mobileValidationRequired;
    }

    public void setMobileValidationRequired(String mobileValidationRequired) {
        this.mobileValidationRequired = mobileValidationRequired;
    }

    public String getMailValidationRequired() {
        return mailValidationRequired;
    }

    public void setMailValidationRequired(String mailValidationRequired) {
        this.mailValidationRequired = mailValidationRequired;
    }

    public String getCardValidationRequired() {
        return cardValidationRequired;
    }

    public void setCardValidationRequired(String cardValidationRequired) {
        this.cardValidationRequired = cardValidationRequired;
    }

    public String getOtpValidationRequired() {
        return otpValidationRequired;
    }

    public void setOtpValidationRequired(String otpValidationRequired) {
        this.otpValidationRequired = otpValidationRequired;
    }
}
