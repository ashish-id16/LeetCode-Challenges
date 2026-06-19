import java.util.HashMap;
import java.util.Map;

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        
        return splitTree(postorder, 0, postorder.length - 1, 0, inorder.length - 1, inMap);
    }
    
    private TreeNode splitTree(int[] postorder, int postStart, int postEnd, 
                               int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRootIndex = inMap.get(root.val);
        int numsLeft = inRootIndex - inStart;
        
        root.left = splitTree(postorder, postStart, postStart + numsLeft - 1, 
                              inStart, inRootIndex - 1, inMap);
                              
        root.right = splitTree(postorder, postStart + numsLeft, postEnd - 1, 
                               inRootIndex + 1, inEnd, inMap);
        
        return root;
    }
}