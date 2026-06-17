class Solution {
    public int[] singleNumber(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        
        int lowestBit = xorSum & -xorSum;
        
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & lowestBit) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        
        return result;
    }
}