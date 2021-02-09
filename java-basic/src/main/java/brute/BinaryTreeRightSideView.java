package brute;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * = 199
 */
public class BinaryTreeRightSideView {
    // Custom Testcases:
    // [1,2,4,null,5,3,null]
    // []
    public List<Integer> rightSideView(TreeNode root) {
        if(null == root) {
            return Collections.EMPTY_LIST;
        }
        Queue<Integer> result = new LinkedList<>();

        return result.stream().collect(Collectors.toList());
    }
}
