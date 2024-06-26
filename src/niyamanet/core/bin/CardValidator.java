package niyamanet.core.bin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardValidator {

    public static void main(String[] args) {
        System.out.println("-----Luns check----");

        String cardNo = "79927398713";
        if (checkLuhn(cardNo))
            System.out.println("This is a valid card");
        else
            System.out.println("This is not a valid card");
    }

    public static Map<String, Boolean> getValidCards(List<String> cardNumbers) {
        Map<String, Boolean> validCards = new HashMap<String, Boolean>();

        for(String cardNo : cardNumbers)
        {
            validCards.put(cardNo, checkLuhn(cardNo));
        }
        return validCards;
    }

    static boolean checkLuhn(String cardNo)
    {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

}
