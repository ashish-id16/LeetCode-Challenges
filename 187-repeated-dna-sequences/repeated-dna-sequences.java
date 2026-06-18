import java.util.*;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return result;
        }
        
        int[] charToBits = new int[256];
        charToBits['A'] = 0;
        charToBits['C'] = 1; 
        charToBits['G'] = 2; 
        charToBits['T'] = 3; 
    
        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        
        int mask = 0;
        for (int i = 0; i < 9; i++) {
            mask = (mask << 2) | charToBits[s.charAt(i)];
        }
        
        int windowMask = 0xFFFFF; 
        
        for (int i = 9; i < s.length(); i++) {
            mask = ((mask << 2) | charToBits[s.charAt(i)]) & windowMask;
            
            if (!seen.add(mask)) {
                repeated.add(s.substring(i - 9, i + 1));
            }
        }
        
        return new ArrayList<>(repeated);
    }
}