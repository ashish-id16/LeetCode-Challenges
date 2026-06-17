import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentList, int k, int target, int start) {
        if (currentList.size() == k) {
            if (target == 0) {
                result.add(new ArrayList<>(currentList));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > target) {
                break;
            }

            currentList.add(i);

            backtrack(result, currentList, k, target - i, i + 1);

            currentList.remove(currentList.size() - 1);
        }
    }
}