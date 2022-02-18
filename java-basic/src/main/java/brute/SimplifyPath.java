package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 71. Simplify Path
 * Runtime: 7 ms, faster than 62.16% of Java online submissions for Simplify Path.
 * Memory Usage: 43.9 MB, less than 19.91% of Java online submissions for Simplify Path.
 */
public class SimplifyPath {
    TreeNode root = new TreeNode("/");

    class TreeNode {
        String name;
        TreeNode parent = null;

        public TreeNode(String name) {
            this(name, null);
        }

        public TreeNode(String name, TreeNode parent) {
            this.name = name;
            this.parent = parent;
        }
    }

    public String simplifyPath(String path) {
        TreeNode current = root;
        int l = path.length();
        String[] splitPath = path.split("/");
        for(int i=0; i<splitPath.length; i++) {
            if(!"".equals(splitPath[i])) {
                if("..".equals(splitPath[i])) {
                    if(current != root) {
                        current = current.parent;
                    }
                } else {
                    if(!".".equals(splitPath[i])) {
                        TreeNode node = new TreeNode(splitPath[i], current);
                        current = node;
                    }
                }
            }
        }
        List<TreeNode> pathList = new ArrayList<>();
        while(current != null) {
            pathList.add(current);
            current = current.parent;
        }
        StringBuilder res = new StringBuilder();
        for(int i=pathList.size()-1; i>=0; i--) {
            res.append(pathList.get(i).name);
            if(i > 0 && i != pathList.size()-1) {
                res.append("/");
            }
        }
        return res.toString();
    }
}
