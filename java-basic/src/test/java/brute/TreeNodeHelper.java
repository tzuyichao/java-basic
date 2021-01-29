package brute;

public class TreeNodeHelper {
    public static TreeNode make(Integer[] data) {
        if(null == data || data.length == 0) {
            return null;
        }
        TreeNode[] dataTreeNode = new TreeNode[data.length];
        for(int i=0; i<data.length; i++) {
            if(data[i] == null) {
                dataTreeNode[i] = null;
            } else {
                dataTreeNode[i] = new TreeNode(data[i]);
            }
        }

        for(int i=0; i<data.length; i++) {
            if(dataTreeNode[i] != null) {
                int leftIndex = 2 * i + 1;
                int rightIndex = 2 * i + 2;

                if(leftIndex < data.length) {
                    dataTreeNode[i].left = dataTreeNode[leftIndex];
                }
                if(rightIndex < data.length) {
                    dataTreeNode[i].right = dataTreeNode[rightIndex];
                }
            }
        }

        return dataTreeNode[0];
    }
}
