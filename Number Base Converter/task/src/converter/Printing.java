package converter;

import java.util.Scanner;

public class Printing {
    private static byte sourceBase, targetBase;
    private String input, toConvert;
    private Scanner scanner = new Scanner(System.in);
    public void formatChoose () {
        System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
        input = scanner.next();
        if (input.equals("/exit")) {
            System.exit(0);
        }
        sourceBase = Byte.parseByte(input);
        input = scanner.next();
        targetBase = Byte.parseByte(input);
        numbersInput();
    }
    public void numbersInput() {
        System.out.print("Enter number in base " +
                sourceBase + " to convert to base " +
                targetBase + " (To go back type /back) ");
        toConvert = scanner.next();
        if (toConvert.equals("/back")) {
            formatChoose();
        } else if (toConvert.equals("0")) {
            System.out.println("Conversion result: 0");
        }
        Converter converter = new Converter(sourceBase, targetBase, toConvert);
    }
}
