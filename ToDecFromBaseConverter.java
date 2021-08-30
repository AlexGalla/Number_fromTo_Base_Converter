package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

public class ToDecFromBaseConverter {

     public static String convert(String userNumber, int sourceBase) {
        if (userNumber.contains(".")) {
            String mainPart = userNumber.toUpperCase(Locale.ROOT).substring(0, userNumber.indexOf("."));
            String fractionPart = userNumber.toUpperCase(Locale.ROOT).substring(userNumber.indexOf(".") + 1);
            String mResult = toDecimalMainConverter(mainPart, sourceBase);
            String fResult = toDecimalFractConverter(fractionPart, sourceBase);
            return (mResult + fResult);
        } else {
            String mResult = toDecimalMainConverter(userNumber.toUpperCase(Locale.ROOT), sourceBase);
            return (mResult);
        }
    }

    //CALCULATING THE MAIN PART OF THE NUMBER
    private static String toDecimalMainConverter(String mainPartString, int sourceBase){
        BigInteger mainPartResult = BigInteger.valueOf(0);
        String additionalNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String reverseMainPart = "";
        for (int i = mainPartString.length() - 1; i >= 0; i--) {
            reverseMainPart = reverseMainPart.concat(String.valueOf(mainPartString.charAt(i)));
        }
        for (int i = 0; i < reverseMainPart.length(); i++) {
            if (additionalNumber.contains(String.valueOf(reverseMainPart.charAt(i)))) {
                //result = result + Math.pow(sourceBase, i) * (additionalNumber.indexOf(reverseNumArray.get(i)) + 10);
                mainPartResult = mainPartResult.add(BigInteger.valueOf((long) Math.pow(sourceBase, i) * (additionalNumber.indexOf(reverseMainPart.charAt(i)) + 10)));
            } else {
                //result = result + Math.pow(sourceBase, i) * Integer.parseInt(reverseNumArray.get(i));
                mainPartResult = mainPartResult.add(BigInteger.valueOf((long) Math.pow(sourceBase, i) * (Integer.parseInt(String.valueOf(reverseMainPart.charAt(i))))));
            }
        }
        return String.valueOf(mainPartResult);
    }

    //CALCULATING THE FRACTIONAL PART OF THE NUMBER
    static String toDecimalFractConverter(String fractPartString, int sourceBase){
        String additionalNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        BigDecimal fractionalResult = BigDecimal.valueOf(0);
        for (int i = 0; i <fractPartString.length(); i++) {
            if (additionalNumber.contains(String.valueOf(fractPartString.charAt(i)))) { //if (additionalNumber.contains(userNumArray.get(i))) {
                //fractionalResult = fractionalResult + (1.0 / Math.pow(sourceBase, i + 1)) * (additionalNumber.indexOf(reverseNumArray.get(i)) + 10);
                fractionalResult = fractionalResult.add(BigDecimal.valueOf((1.0 / Math.pow(sourceBase, i + 1)) * (additionalNumber.indexOf(String.valueOf(fractPartString.charAt(i))) + 10)));
            } else {
                //fractionalResult = fractionalResult + (1.0 / (Math.pow(sourceBase, i + 1)) * Integer.parseInt(userNumArray.get(i)));
                fractionalResult = fractionalResult.add(BigDecimal.valueOf((1.0 / Math.pow(sourceBase, i + 1)) * Long.parseLong(String.valueOf(fractPartString.charAt(i)))));
            }
        }
        if (String.valueOf(fractionalResult).substring(String.valueOf(fractionalResult).indexOf(".")).length() > 5) {
            return String.valueOf(fractionalResult).substring(String.valueOf(fractionalResult).indexOf("."), 5);
        } else {
            return (String.valueOf(fractionalResult).substring(String.valueOf(fractionalResult).indexOf(".")));
        }
    }


}
