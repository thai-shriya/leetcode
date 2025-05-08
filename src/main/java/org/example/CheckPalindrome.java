package org.example;

public class CheckPalindrome {
    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<=j){
            char left = s.charAt(i);
            char right = s.charAt(j);

            if(!Character.isLetterOrDigit(left)){
                i++;
                continue;
            }

            if(!Character.isLetterOrDigit(right)){
                j--;
                continue;
            }

            if(Character.toLowerCase(left)!=Character.toLowerCase(right))
                return false;

            i++;
            j--;
        }

        return true;

    }

    public static void main(String[] args) {

        CheckPalindrome checker = new CheckPalindrome();

        String[] testCases = {
                "A man, a plan, a canal: Panama",  // true
                "race a car",                      // false
                " ",                               // true (empty or space-only string is a palindrome)
                "No lemon, no melon",              // true
                "Was it a car or a cat I saw?",    // true
                "not a palindrome"                 // false
        };

        for (String test : testCases) {
            boolean result = checker.isPalindrome(test);
            System.out.println("\"" + test + "\" -> " + result);
        }

    }
}
