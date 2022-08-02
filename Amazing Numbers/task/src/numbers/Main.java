package numbers;

import java.util.*;


class AmazingNumbers {

    HashSet<String> properParams = new HashSet<>(Set.of("buzz", "duck", "palindromic", "gapful", "spy", "square",
            "sunny", "jumping", "even", "odd", "happy", "sad"));

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

    private boolean isSunny(long n) {
        return isPerfectSquare(n + 1);
    }

    private boolean isPerfectSquare(long n) {
        double d = Math.sqrt(n);
        return d == (double) (int) d;
    }

    private boolean isJumping(long n) {
        if (n < 10) {
            return true;
        }

        long prev = n % 10;
        n /= 10;
        while (n != 0) {
            long val = n % 10;
            n /= 10;

            if (Math.abs(prev - val) != 1) {
                return false;
            }

            prev = val;
        }

        return true;
    }

    private boolean isHappy(long n) {
        do {
            int sum = 0;
            while (n != 0) {
                long val = n % 10;
                sum += val * val;
                n /= 10;
            }
            n = sum;
        } while (n > 9);

        return n == 1;
    }

    private boolean isSad(long n) {
        return !isHappy(n);
    }

    public void greeting() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    public void instructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    public void properties(Long n) {

        if (!is1ParameterCorrect(n)) return;

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

        // square
        boolean square = isPerfectSquare(n);

        // sunny
        boolean sunny = isSunny(n);

        // jumping
        boolean jumping = isJumping(n);

        // happy/sad
        boolean happy = isHappy(n);
        boolean sad = isSad(n);

        System.out.println("Properties of " + n +
                "\n        buzz: " + buzz +
                "\n        duck: " + duck +
                "\n palindromic: " + palindromic +
                "\n      gapful: " + gapful +
                "\n         spy: " + spy +
                "\n      square: " + square +
                "\n       sunny: " + sunny +
                "\n     jumping: " + jumping +
                "\n       happy: " + happy +
                "\n         sad: " + sad +
                "\n        even: " + even +
                "\n         odd: " + odd + "\n");

    }

    public void propertiesList(Long n, Long k) {

        if (!is1ParameterCorrect(n)) return;
        if (!is2ParameterCorrect(k)) return;

        for (long i = n; i < n + k; i++) {

            StringBuilder str = new StringBuilder();

            if (isBuzz(i)) str.append("buzz, ");
            if (isDuck(i)) str.append("duck, ");
            if (isPalindromic(i)) str.append("palindromic, ");
            if (isGapful(i)) str.append("gapful, ");
            if (isSpy(i)) str.append("spy, ");
            if (isPerfectSquare(i)) str.append("square, ");
            if (isSunny(i)) str.append("sunny, ");
            if (isJumping(i)) str.append("jumping, ");
            if (isHappy(i)) str.append("happy, ");
            if (isSad(i)) str.append("sad, ");
            if (isEven(i)) str.append("even, ");
            if (isOdd(i)) str.append("odd, ");

            System.out.println("              \t" + i + " is " + str.substring(0, str.lastIndexOf(",")).toString());
        }

        System.out.println();
    }

    public boolean getProperty(Long n, String v) {
        String w = v.charAt(0) == '-' ? v.substring(1) : String.valueOf(v);

        boolean result = switch (w) {
            case "buzz" -> isBuzz(n);
            case "duck" -> isDuck(n);
            case "palindromic" -> isPalindromic(n);
            case "gapful" -> isGapful(n);
            case "spy" -> isSpy(n);
            case "square" -> isPerfectSquare(n);
            case "sunny" -> isSunny(n);
            case "jumping" -> isJumping(n);
            case "happy" -> isHappy(n);
            case "sad" -> isSad(n);
            case "even" -> isEven(n);
            case "odd" -> isOdd(n);
            default -> false;
        };

        if (v.charAt(0) == '-') {
            result = !result;
        }

        return result;
    }

    public void propertiesListCount(Long n, Long k, List<String> params) {

        if (!is1ParameterCorrect(n)) return;
        if (!is2ParameterCorrect(k)) return;
        if (!is3PlusParametersCorrect(params)) return;

        int count = 0;
        while (count < k) {

            boolean propertiesCorrect = true;
            for (var param : params) {
                propertiesCorrect = propertiesCorrect && getProperty(n, param);
            }

            if (propertiesCorrect) {
                count++;

                StringBuilder str = new StringBuilder();

                if (isBuzz(n)) str.append("buzz, ");
                if (isDuck(n)) str.append("duck, ");
                if (isPalindromic(n)) str.append("palindromic, ");
                if (isGapful(n)) str.append("gapful, ");
                if (isSpy(n)) str.append("spy, ");
                if (isPerfectSquare(n)) str.append("square, ");
                if (isSunny(n)) str.append("sunny, ");
                if (isJumping(n)) str.append("jumping, ");
                if (isHappy(n)) str.append("happy, ");
                if (isSad(n)) str.append("sad, ");
                if (isEven(n)) str.append("even, ");
                if (isOdd(n)) str.append("odd, ");

                System.out.println("              \t" + n + " is " + str.substring(0, str.lastIndexOf(",")));
            }

            n++;
        }

        System.out.println();
    }

    public boolean is1ParameterCorrect(Long n) {
        if (n == null || n < 0) {
            System.out.println("The first parameter should be a natural number or zero.\n");
            return false;
        }

        return true;
    }

    public boolean is2ParameterCorrect(Long k) {
        if (k == null || k < 1) {
            System.out.println("The second parameter should be a natural number.\n");
            return true;
        }

        return true;
    }

    public boolean isParameterCorrect(String v) {
        return properParams.contains(v) || v.charAt(0) == '-' && properParams.contains(v.substring(1));
    }

    public void parametersError(List<String> badParams) {
        StringBuilder str = new StringBuilder();
        for (var badParam: badParams) {
            str.append(badParam.toUpperCase());
            str.append(", ");
        }

        String value = str.substring(0, str.lastIndexOf(","));

        if (badParams.size() == 1) {
            System.out.printf("The property [%s] is wrong.\n\n", value);
        } else {
            System.out.printf("The properties [%s] are wrong.\n\n", value);
        }

        StringBuilder str2 = new StringBuilder();
        for (var param : properParams) {
            str2.append(param.toUpperCase());
            str2.append(", ");
        }

        String value2 = str2.substring(0, str2.lastIndexOf(","));
        System.out.printf("Available properties: [%s]\n\n", value2);

    }

    public boolean is3PlusParametersCorrect(List<String> params) {

        List<String> badParams = new ArrayList<>();
        for (var param : params) {
            param = param.toLowerCase();

            if (!isParameterCorrect(param)) {
                badParams.add(param);
            }
        }

        if (badParams.size() != 0) {
            parametersError(badParams);
            return false;
        }

        StringBuilder str = new StringBuilder();

        for (var element: properParams) {
            if (params.contains(element) && params.contains("-" + element)) str.append(element + ", -" + element + ", ");
        }

        if (params.contains("even") && params.contains("odd")) str.append("even, odd, ");
        if (params.contains("-even") && params.contains("-odd")) str.append("-even, -odd, ");
        if (params.contains("duck") && params.contains("spy")) str.append("duck, spy, ");
        if (params.contains("sunny") && params.contains("square")) str.append("sunny, square, ");
        if (params.contains("happy") && params.contains("sad")) str.append("happy, sad, ");
        if (params.contains("-happy") && params.contains("-sad")) str.append("-happy, -sad, ");

        if (str.length() != 0) {

            String value = str.substring(0, str.lastIndexOf(",")).toUpperCase();
            System.out.println("The request contains mutually exclusive properties: [" + value + "]\n" +
                    "There are no numbers with these properties.\n");

            return false;
        }

        return true;
    }

    public void menu() {

        System.out.print("Enter a request: ");

        Scanner scanner = new Scanner(System.in);
        String parameters = scanner.nextLine().trim().toLowerCase();
        System.out.println();

        while (true) {

            Long n1 = null;
            Long n2 = null;
            String[] params = parameters.split("\\s");

            if (params.length == 1) {
                try {
                    n1 = Long.parseLong(params[0]);
                } catch (NumberFormatException e) {
                }

                if (n1 != null && n1 == 0) {
                    break;
                }
            } else if (params.length >= 2) {
                try {
                    n1 = Long.parseLong(params[0]);
                    n2 = Long.parseLong(params[1]);
                } catch (NumberFormatException e) {
                }
            }

            if (parameters.isBlank()) {
                instructions();
            } else if (params.length == 1) {
                properties(n1);
            } else if (params.length == 2) {
                propertiesList(n1, n2);
            } else if (params.length >= 3) {
                List<String> paramsList = new ArrayList<>();
                for (int i = 2; i < params.length; i++) {
                    paramsList.add(params[i]);
                }
                propertiesListCount(n1, n2, paramsList);
            }

            System.out.print("Enter a request: ");
            parameters = scanner.nextLine().trim().toLowerCase();
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
