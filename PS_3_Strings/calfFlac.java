package myUsacoSilverSolutions.PS_3_Strings;

import java.util.Scanner;

public class calfFlac {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input string:");
        String input = scanner.nextLine();

        // Iterate through chars (Our start index)
        for (int start = 0; start < input.length(); start++) {

            // Find the same char starting from the end
            for (int end = input.length() - 1; end >= start; end--) {
                String substring = input.substring(start, end + 1);
                String cleanedStr = cleanString(substring);
                
                if (isPalindrome(cleanedStr)) {
                    System.out.println("Palindrome Length: " + (end - start + 1));
                    System.out.println("Palindrome Substring: " + substring);
                }
            }
        }
    }

    public static String cleanString(String str) {
        // Remove punctuation and spaces, and convert to lowercase
        return str.replaceAll("[\\p{Punct}\\s]", "").toLowerCase();
    }

    public static boolean isPalindrome(String str) {
        // Use StringBuilder to reverse the string and check if it matches the original
        String reversedStr = new StringBuilder(str).reverse().toString();
        return str.equals(reversedStr);
    }
}