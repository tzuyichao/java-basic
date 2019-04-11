package basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private static final Logger logger = LoggerFactory.getLogger("Lab");
    private T content;
    private boolean marked;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public TreeNode(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
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

    public TreeNode find(T content) {
        if(content.equals(content)) {
            return this;
        } else {
            for(TreeNode treeNode: children) {
                return treeNode.find(content);
            }
        }
        return null;
    }

    public void add(T parentContent, T currentContent) {
        if(content.equals(parentContent)) {
            if(!children.stream().anyMatch(node -> node.getContent().equals(currentContent))) {
                children.add(new TreeNode(currentContent));
            }
        } else {
            for(TreeNode treeNode: children) {
                TreeNode targetNode = treeNode.find(parentContent);
                if(targetNode != null) {
                    List<TreeNode> targetNodeChildren = targetNode.getChildren();
                    if(!targetNodeChildren.stream().anyMatch(node -> node.getContent().equals(currentContent))) {
                        targetNodeChildren.add(new TreeNode(currentContent));
                    }
                } else {
                    // Error parent not add
                    logger.error("Parent node not found {}", parentContent);
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

        result.append("{\"content\": \"").append(content).append("\",");
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
