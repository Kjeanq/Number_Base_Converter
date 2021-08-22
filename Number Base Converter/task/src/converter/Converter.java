package converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Converter {
    public Converter(byte sourceBase, byte targetBase, String toConert) {
        String[] fractionCheck = toConert.split("\\.");
        BigInteger temp = new BigInteger(fractionCheck[0], sourceBase);
        if (fractionCheck.length == 2) {
            System.out.println("Conversion result: " + temp.toString(targetBase)
                    + toDecimal(sourceBase, targetBase, fractionCheck[1]));
        } else {
            System.out.println("Conversion result: " + temp.toString(targetBase));
        }

        Printing x = new Printing();
        x.numbersInput();
    }

    private String toDecimal (byte sourceBase, byte targetBase, String fraction) {
        if (new BigInteger(fraction, sourceBase).equals(new BigInteger("0"))) {
            return "." + fraction;
        }
        String inDecimal = "0";
        char[] tableToConversion = fraction.toCharArray();
        long iterator = -1;
        for (int i = 0; i < tableToConversion.length; i++) {
            int temp = tableToConversion[i];
            if (temp > 47 && temp < 58) {
                temp -= 48;
            } else if (temp > 64 && temp < 91) {
                temp -= 55;
            } else if (temp > 96 && temp < 123) {
                temp -= 87;
            }
            BigDecimal current = BigDecimal.valueOf(temp * Math.pow(sourceBase, iterator));
            BigDecimal past = new BigDecimal(inDecimal);
            inDecimal = String.valueOf(new BigDecimal(String.valueOf(current.add(past))));
            iterator--;
        }
        if (targetBase == 10) {
            return lenghtControl(fraction, inDecimal);
        } else {
            return toTarget(targetBase, inDecimal, fraction);
        }
    }
    private String toTarget (byte targetBase, String decimalFraction, String fraction){
        String control = "0.";
        String fractionTarget = ".";
        for (int i = 0; i < decimalFraction.length() - 2; i++){
            control += "0";
        }
        while (fractionTarget.length() < 6 && fractionTarget.length() <= fraction.length()) {
            BigDecimal toConv = new BigDecimal(decimalFraction);
            if (toConv.equals(new BigDecimal(control))) {
                return lenghtControl(fraction, fractionTarget);
            }
            BigDecimal temp = toConv.multiply(BigDecimal.valueOf(targetBase));
            String s = String.valueOf(temp.divideToIntegralValue(new BigDecimal("1")));
            int x = new Double(s).intValue();
            if (x < 10) {
                fractionTarget += x;
            } else {
                char t = (char) (x + 87);
                fractionTarget += t;
            }
            decimalFraction = String.valueOf(temp.remainder(new BigDecimal("1")));
        }
        return lenghtControl(fraction, fractionTarget);
    }
    private String lenghtControl(String toConvert, String result) {
        int x = 5 - (result.length() -1);
        for (int i = 0; i < x; i++){
            result +="0";
            System.out.println(result);
        }
        return result;
    }


}
