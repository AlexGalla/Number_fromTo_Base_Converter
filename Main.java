package converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean flagMain = true;
            String input;
            int source;
            int target;

            while (flagMain) {
                System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
                input = reader.readLine();
                if (input.equals("/exit")) {
                    flagMain = false;
                } else {
                    if (input.split(" ").length > 2) {
                        System.out.println("Wrong input format...");
                    } else {
                        source = Integer.parseInt(input.split(" ")[0].trim());
                        target = Integer.parseInt(input.split(" ")[1].trim());
                        //System.out.println("Source is: " + source + ", Target is: " + target);
                        boolean flagSecond = true;

                        while (flagSecond) {
                            System.out.print("Enter number in base " + source +" to convert to base " + target + " (To go back type /back) ");
                            input = reader.readLine();
                            if (input.equals("/back")) {
                                flagSecond = false;
                            } else {
                                System.out.println("Conversion result: " + FromDecToBaseConverter.convert(ToDecFromBaseConverter.convert(input, source), target));
                            }
                            System.out.println();
                        }
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Input error occurred...");
        }
    }
}
