package converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FromDecToBaseConverter {

    public static String convert(String userNumber, int targetBase) {
        if (userNumber.contains(".")) {
            String mainPart = userNumber.substring(0, userNumber.indexOf("."));
            String fractionPart = userNumber.substring(userNumber.indexOf("."));
            String mResult = fromDecimalMainConverter(mainPart, targetBase);
            String fResult = fromDecimalFractConverter(fractionPart, targetBase);
            return (mResult + fResult);
        } else {
            String mResult = fromDecimalMainConverter(userNumber, targetBase);
            return (mResult);
        }
    }

    ///CONVERTING MAIN NUMBER PART TO TARGET BASE
    private static String fromDecimalMainConverter(String mainPartString, int targetBase){
        BigInteger mainPart = new BigInteger(mainPartString);
        String tempString = "";
        String resultString = "";
        String additionalNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (mainPart.compareTo(BigInteger.valueOf(0)) > 0) { //(number > 0) {
            if (mainPart.remainder(BigInteger.valueOf(targetBase)).compareTo(BigInteger.valueOf(10)) >= 0) { //if (number%targetBase >= 10) {
                //resultStringArray.add(additionalNumber.get((number % targetBase) - 10));
                tempString = tempString.concat(String.valueOf(additionalNumber.charAt(mainPart.remainder(BigInteger.valueOf(targetBase)).subtract(BigInteger.valueOf(10)).intValue())));
            } else {
                //resultStringArray.add(Integer.toString(number % targetBase));
                tempString = tempString.concat(mainPart.remainder(BigInteger.valueOf(targetBase)).toString());
            }
            mainPart = mainPart.divide(BigInteger.valueOf(targetBase)); //number = number / targetBase;
        }
        for (int i = tempString.length() - 1; i >= 0; i--) {
            resultString = resultString.concat(String.valueOf(tempString.charAt(i)));
        }
        return resultString;
    }

    //CONVERTING FRACTIONAL NUMBER PART TO TARGET BASE
    private static String fromDecimalFractConverter(String fractionPartString, int targetBase){
        int precisionCounter = 0;
        int precision = 5;
        String fractionResultString = ".";
        BigDecimal fractionPart = new BigDecimal("0" + fractionPartString);
        String additionalNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (precisionCounter < precision) {
            fractionPart = fractionPart.multiply(BigDecimal.valueOf(targetBase)); //fractionPart = fractionPart * targetBase;
            int mainPartTemp = Integer.parseInt(String.valueOf(fractionPart).substring(0, String.valueOf(fractionPart).indexOf(".")));
            if (mainPartTemp >= 10) {
                fractionResultString = fractionResultString.concat(String.valueOf(additionalNumber.charAt(mainPartTemp - 10)));
            } else {
                fractionResultString = fractionResultString.concat(String.valueOf(mainPartTemp));
            }
            fractionPart = fractionPart.remainder(BigDecimal.valueOf(1)); //fractionPart = fractionPart % 1;
            precisionCounter++;
        }
        return fractionResultString;
    }

}
