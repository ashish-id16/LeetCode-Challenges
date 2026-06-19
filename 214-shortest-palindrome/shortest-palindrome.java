class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        
        String reversed = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + reversed;
        
        int[] lps = new int[combined.length()];
        for (int i = 1; i < combined.length(); i++) {
            int j = lps[i - 1];
            while (j > 0 && combined.charAt(i) != combined.charAt(j)) {
                j = lps[j - 1];
            }
            if (combined.charAt(i) == combined.charAt(j)) {
                j++;
            }
            lps[i] = j;
        }
        
        int longestPalindromePrefixLength = lps[combined.length() - 1];
        String suffixToAdd = reversed.substring(0, s.length() - longestPalindromePrefixLength);
        
        return suffixToAdd + s;
    }
}