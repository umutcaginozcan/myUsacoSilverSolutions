import java.util.Scanner;

public class calfFlac {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            input += line + "\n";
        }
        
        int maxLen = 0;
        String maxStr = "";
        // Iterate through chars (Our start index)
        for (int start = 0; start < input.length(); start++) {

            // Find the same char starting from the end
            for (int end = input.length() - 1; end >= start; end--) {
                String substring = input.substring(start, end + 1);
                String cleanedStr = cleanString(substring);
                
                if (isPalindrome(cleanedStr) && cleanedStr.length() > maxLen) {
                    maxLen = cleanedStr.length();
                    maxStr = substring;
                }
            }
        }
        System.out.println(maxLen);
        System.out.println(finalizer(maxStr));
    }

    public static String cleanString(String str) {
        // Remove punctuation and spaces, and convert to lowercase
        return str.replaceAll("[\\p{Punct}\\s]", "").toLowerCase();
    }

    public static boolean isPalindrome(String str) {
        String reversedStr = new StringBuilder(str).reverse().toString();
        return str.equals(reversedStr);
    }

    public static String finalizer(String maxStr) {
        String str = maxStr.toLowerCase();
        int start = 0, end = 0;
        for (int i = 0; i < maxStr.length(); i++) {
            if (str.charAt(i) - 'a' <= 31 && str.charAt(i) - 'a' >= 0) {
                start = i;
                break;
            }
        }
        for (int i = maxStr.length() - 1; i >= 0; i--) {
            if (str.charAt(i) - 'a' <= 31 && str.charAt(i) - 'a' >= 0) {
                end = i;
                break;
            }
        }
        return maxStr.substring(start, end + 1);
    }
}