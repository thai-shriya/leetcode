package org.example;

public class CheckString {
    public boolean detectCapitalUse(String word) {
        int lower =0, upper =0;
        for(int i=0;i<word.length();i++){
            char s = word.charAt(i);
            if(Character.isLowerCase(s))
                lower++;
            else
                upper++;
        }

        if(upper == word.length() || lower == word.length() || (lower == word.length() -1 && upper == 1 && Character.isUpperCase(word.charAt(0))))
            return true;
        return false;

    }
}
