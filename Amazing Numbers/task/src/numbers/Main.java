package numbers;

import java.util.Scanner;


class AmazingNumbers {

    private boolean isDuck(long n) {
        while (n != 0) {
            if (n / 10 != 0 && n % 10 == 0) {
                return true;
            }

            n /= 10;
        }

        return false;
    }

    private boolean isBuzz(long n) {
        return n % 10 == 7 || n % 7 == 0;
    }

    private boolean isEven(long n) {
        return n % 2 == 0;
    }

    private boolean isOdd(long n) {
        return n % 2 != 0;
    }

    private boolean isPalindromic(long n) {

        String str = String.valueOf(n);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean isGapful(long n) {

        if (n < 100) {
            return false;
        }

        String str = String.valueOf(n);
        int value = Integer.parseInt("" + str.charAt(0) + str.charAt(str.length() - 1));

        return n % value == 0;
    }

    private boolean isSpy(long n) {

        if (n < 10) {
            return true;
        }

        long sum = 0;
        long mult = 1;

        while (n != 0) {
            long val = n % 10;
            sum += val;
            mult *= val;
            n /= 10;
        }

        return sum == mult;
    }

    public void greeting() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    public void instructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    public void properties(long n) {

        if (n < 1) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }

        // even/odd
        boolean even = isEven(n);
        boolean odd = isOdd(n);

        // buzz
        boolean buzz = isBuzz(n);

        // duck
        boolean duck = isDuck(n);

        // palindromic
        boolean palindromic = isPalindromic(n);

        // gapful
        boolean gapful = isGapful(n);

        // spy
        boolean spy = isSpy(n);

        System.out.println("Properties of " + n +
                "\n        buzz: " + buzz +
                "\n        duck: " + duck +
                "\n palindromic: " + palindromic +
                "\n      gapful: " + gapful +
                "\n         spy: " + spy +
                "\n        even: " + even +
                "\n         odd: " + odd + "\n");

    }

    public void propertiesList(long n, Long k) {

        if (n < 1) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }

        if (k < 1) {
            System.out.println("The second parameter should be a natural number.\n");
            return;
        }

        for (long i = n; i < n + k; i++) {

            StringBuilder str = new StringBuilder();

            if (isBuzz(i)) str.append("buzz, ");
            if (isDuck(i)) str.append("duck, ");
            if (isPalindromic(i)) str.append("palindromic, ");
            if (isGapful(i)) str.append("gapful, ");
            if (isSpy(i)) str.append("spy, ");
            if (isEven(i)) str.append("even, ");
            if (isOdd(i)) str.append("odd, ");

            System.out.println("              \t" + i + " is " + str.substring(0, str.lastIndexOf(",")).toString());
        }

        System.out.println();
    }

    public void propertiesListCount(long n, Long k, String v) {

        if (n < 1) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }

        if (k < 1) {
            System.out.println("The second parameter should be a natural number.\n");
            return;
        }

        if (!(v.equals("buzz") || v.equals("duck") || v.equals("palindromic") || v.equals("gapful") ||
                v.equals("spy") || v.equals("even") || v.equals("odd"))) {

            System.out.println("The property [" + v.toUpperCase() + "] is wrong.\n" +
                    "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]\n");
            return;
        }

        int count = 0;
        while (count < k) {

            boolean property = switch (v) {
                case "buzz" -> isBuzz(n);
                case "duck" -> isDuck(n);
                case "palindromic" -> isPalindromic(n);
                case "gapful" -> isGapful(n);
                case "spy" -> isSpy(n);
                case "even" -> isEven(n);
                case "odd" -> isOdd(n);
                default -> false;
            };

            if (!property) {
                n++;
                continue;
            }

            StringBuilder str = new StringBuilder();

            if (isBuzz(n)) str.append("buzz, ");
            if (isDuck(n)) str.append("duck, ");
            if (isPalindromic(n)) str.append("palindromic, ");
            if (isGapful(n)) str.append("gapful, ");
            if (isSpy(n)) str.append("spy, ");
            if (isEven(n)) str.append("even, ");
            if (isOdd(n)) str.append("odd, ");

            System.out.println("              \t" + n + " is " + str.substring(0, str.lastIndexOf(",")).toString());

            count++;
            n++;
        }

        System.out.println();
    }

    public void menu() {

        System.out.print("Enter a request: ");

        Scanner scanner = new Scanner(System.in);
        String parameters = scanner.nextLine().trim();
        System.out.println();

        while (true) {

            Long n1 = null;
            Long n2 = null;
            String n3 = null;

            int delimiterIndex = parameters.indexOf(" ");
            if (delimiterIndex == -1) {
                try {
                    n1 = Long.parseLong(parameters);
                } catch (NumberFormatException e) {
                }
            } else {
                int delimiterIndex2 = parameters.lastIndexOf(" ");
                if (delimiterIndex == delimiterIndex2) {
                    try {
                        n1 = Long.parseLong(parameters.substring(0, delimiterIndex));
                        n2 = Long.parseLong(parameters.substring(delimiterIndex + 1));
                    } catch (NumberFormatException e) {
                    }
                } else {
                    try {
                        n1 = Long.parseLong(parameters.substring(0, delimiterIndex));
                        n2 = Long.parseLong(parameters.substring(delimiterIndex + 1, delimiterIndex2));
                    } catch (NumberFormatException e) {
                    }
                    n3 = parameters.substring(delimiterIndex2 + 1).toLowerCase();
                }
            }

            if (parameters.isBlank()) {
                instructions();
            } else if (n1 == null) {
                System.out.println("The first parameter should be a natural number or zero.\n");
            } else if (n1 == 0) {
                break;
            } else if (n2 == null) {
                properties(n1);
            } else if (n2 < 1) {
                System.out.println("The second parameter should be a natural number.\n");
            } else if (n3 == null) {
                propertiesList(n1, n2);
            } else if (!(n3.equals("buzz") || n3.equals("duck") || n3.equals("palindromic") || n3.equals("gapful") ||
                    n3.equals("spy") || n3.equals("even") || n3.equals("odd"))) {

                System.out.println("The property [" + n3.toUpperCase() + "] is wrong.\n" +
                        "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]\n");
            } else {
                propertiesListCount(n1, n2, n3);
            }

            System.out.print("Enter a request: ");
            parameters = scanner.nextLine().trim();
            System.out.println();
        }

        System.out.println("Goodbye!");

        scanner.close();

    }

}

public class Main {

    public static void main(String[] args) {

        AmazingNumbers numbers = new AmazingNumbers();
        numbers.greeting();
        numbers.instructions();
        numbers.menu();

    }
}
