package brute;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        StringBuilder path = new StringBuilder();
        List<String> paths = new ArrayList<>();
        buildPath(root, path, paths);
        return paths;
    }

    private void buildPath(TreeNode root, StringBuilder path, List<String> paths) {
        if(root == null) {
            return;
        }
        System.out.println(STR."\{path};\{paths};\{root.val}");
        int len = path.length();
        path.append(root.val);
        if(root.left == null && root.right == null) {
            paths.add(path.toString());
        } else {
            path.append("->");
            buildPath(root.left, path, paths);
            buildPath(root.right, path, paths);
        }
        path.setLength(len);
    }
}
