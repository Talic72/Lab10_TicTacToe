import java.util.Scanner;

public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String input = "";
        System.out.println(prompt);
        input = pipe.nextLine();

        while (input.length() == 0) {
            System.out.println("Input cannot be blank. Try again.");
            System.out.println(prompt);
            input = pipe.nextLine();
        }

        return input;
    }

    public static int getInt(Scanner pipe, String prompt) {
        boolean valid = false;
        int value = 0;

        while (!valid) {
            System.out.println(prompt);
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                valid = true;
            } else {
                System.out.println("Not a valid integer. Try again.");
                pipe.next(); // throw away invalid input
            }
            pipe.nextLine();
        }
        return value;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        boolean isGood = false;
        double number = 0;

        while (!isGood) {
            System.out.println(prompt);
            if (pipe.hasNextDouble()) {
                number = pipe.nextDouble();
                isGood = true;
            } else {
                System.out.println("That’s not a double! Try again.");
                pipe.next(); // clear bad input
            }
            pipe.nextLine(); // always clear the newline
        }

        return number;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int input = 0;
        boolean ok = false;

        while (!ok) {
            System.out.println(prompt + " [" + low + " - " + high + "]");
            if (pipe.hasNextInt()) {
                input = pipe.nextInt();
                if (input >= low && input <= high) {
                    ok = true;
                } else {
                    System.out.println("Number not in range!");
                }
            } else {
                System.out.println("That’s not an int!");
                pipe.next(); // get rid of bad stuff
            }
            pipe.nextLine(); // clear newline
        }

        return input;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double val = 0;
        boolean done = false;

        while (!done) {
            System.out.println(prompt + " [" + low + " - " + high + "]");
            if (pipe.hasNextDouble()) {
                val = pipe.nextDouble();
                if (val >= low && val <= high) {
                    done = true;
                } else {
                    System.out.println("Number not in range.");
                }
            } else {
                System.out.println("Bad input. Please enter a number.");
                pipe.next();
            }
            pipe.nextLine();
        }

        return val;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String answer = "";
        boolean result = false;
        boolean done = false;

        while (!done) {
            System.out.println(prompt + " [Y/N]");
            answer = pipe.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                result = true;
                done = true;
            } else if (answer.equalsIgnoreCase("N")) {
                result = false;
                done = true;
            } else {
                System.out.println("Please enter Y or N.");
            }
        }

        return result;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input = "";
        boolean match = false;

        while (!match) {
            System.out.println(prompt);
            input = pipe.nextLine();

            if (input.matches(regEx)) {
                match = true;
            } else {
                System.out.println("That doesn’t match the pattern.");
            }
        }

        return input;
    }
}