package basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeNode<T> {
    private static final Logger logger = LoggerFactory.getLogger("Lab");
    private T id;
    private boolean marked;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public TreeNode(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    private void recursiveFind(T id, Set<T> visited, Set<TreeNode> result) {
        visited.add(this.id);
        if(getId().equals(id)) {
            result.add(this);
            return;
        } else {
            for(TreeNode treeNode: this.children) {
                treeNode.recursiveFind(id, visited, result);
            }
        }
    }

    public TreeNode find(T id) {
        if(getId().equals(id)) {
            return this;
        } else {
            Set<T> visited = new HashSet<>();
            Set<TreeNode> result = new HashSet<>();
            for(TreeNode treeNode: this.children) {
                recursiveFind(id, visited, result);
            }
            if(result.size() == 0) {
                return null;
            } else if(result.size() == 1){
                return result.stream().findFirst().get();
            } else {
                logger.error("result size > 1. {}", result);
                return result.stream().findFirst().get();
            }
        }
    }

    public void add(T parentContent, T currentContent) {
        if(id.equals(parentContent)) {
            if(!children.stream().anyMatch(node -> node.getId().equals(currentContent))) {
                children.add(new TreeNode(currentContent));
            }
        } else {
            for(TreeNode treeNode: children) {
                TreeNode targetNode = treeNode.find(parentContent);
                if(targetNode != null) {
                    List<TreeNode> targetNodeChildren = targetNode.getChildren();
                    if(!targetNodeChildren.stream().anyMatch(node -> node.getId().equals(currentContent))) {
                        targetNodeChildren.add(new TreeNode(currentContent));
                    }
                }
            }
        }
    }

    /**
     * 土炮法
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("{\"content\": \"").append(id).append("\",");
        result.append("\"marked\": ").append(marked).append(",");
        result.append("\"children\": [");
        for(int i=0; i<children.size(); i++) {
            if(i == children.size()-1) {
                result.append(children.get(i));
            } else {
                result.append(children.get(i)).append(",");
            }
        }
        result.append("]}");

        return result.toString();
    }
}
