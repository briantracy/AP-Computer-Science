import java.util.Scanner;

public class Main {

    private static final String[] numbers = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int iteration = 0;

        for (;; iteration++) {

        }
    }

    private static boolean contains(String[] arr, String obj) {
        for (String in : arr) {
            if (in.equals(obj)) {
                return true;
            }
        }
        return false;
    }
}
