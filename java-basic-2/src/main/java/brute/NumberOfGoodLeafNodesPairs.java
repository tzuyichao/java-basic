package brute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://zxi.mytechroad.com/blog/tree/leetcode-1530-number-of-good-leaf-nodes-pairs/
 */
public class NumberOfGoodLeafNodesPairs {

    public int countPairs(TreeNode root, int distance) {
        Map<TreeNode, TreeNode> parents = new java.util.HashMap<>();
        List<TreeNode> leaves = new ArrayList<>();

        dfs(root, null, parents, leaves);

        Map<TreeNode, Integer> a = new HashMap<>();

        int result = 0;

        for(int i=0; i<leaves.size(); i++) {
            TreeNode leaf = leaves.get(i);
            a.clear();
            for(int j=0; j<distance && leaf != null; j++, leaf = parents.get(leaf)) {
                a.put(leaf, j);
            }
            for(int k=i+1; k<leaves.size(); k++) {
                result += isGood(leaves.get(k), a, parents, distance);
            }
        }

        return result;
    }

    private void dfs(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parents, List<TreeNode> leaves) {
        if (node == null) return;
        parents.put(node, parent);
        if (node.left == null && node.right == null) {
            leaves.add(node);
        }
        dfs(node.left, node, parents, leaves);
        dfs(node.right, node, parents, leaves);
    }

    private int isGood(TreeNode node, Map<TreeNode, Integer> a, Map<TreeNode, TreeNode> parents, int distance) {
        for (int i = 0; i < distance && node != null; ++i, node = parents.get(node)) {
            if (a.containsKey(node) && a.get(node) + i <= distance) {
                return 1;
            }
        }
        return 0;
    }
}
