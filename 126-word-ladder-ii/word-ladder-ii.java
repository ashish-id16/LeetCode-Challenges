import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }

        Map<String, Integer> distances = new HashMap<>();
        distances.put(beginWord, 0);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                int currDistance = distances.get(currWord);

                if (currWord.equals(endWord)) {
                    found = true;
                    break;
                }

                char[] chars = currWord.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        chars[j] = c;
                        String nextWord = new String(chars);

                        if (dict.contains(nextWord)) {
                            if (!distances.containsKey(nextWord)) {
                                distances.put(nextWord, currDistance + 1);
                                queue.add(nextWord);
                            }
                        }
                    }
                    chars[j] = originalChar; 
                }
            }
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, distances, path, res);
        }

        return res;
    }

    private void backtrack(String currWord, String beginWord, Map<String, Integer> distances, 
                           List<String> path, List<List<String>> res) {
        if (currWord.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath); 
            res.add(validPath);
            return;
        }

        int currDistance = distances.get(currWord);
        char[] chars = currWord.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) continue;
                chars[i] = c;
                String prevWord = new String(chars);

                if (distances.containsKey(prevWord) && distances.get(prevWord) == currDistance - 1) {
                    path.add(prevWord);
                    backtrack(prevWord, beginWord, distances, path, res);
                    path.remove(path.size() - 1);
                }
            }
            chars[i] = originalChar;
        }
    }
}