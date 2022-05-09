package brute;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 341. Flatten Nested List Iterator
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 *
 * ref: https://www.cnblogs.com/grandyang/p/5358793.html
 */
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack = new Stack();

    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i=nestedList.size()-1; i>=0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        NestedInteger top = stack.pop();
        return top.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.empty()) {
            NestedInteger top = stack.peek();
            if(top.isInteger()) {
                return true;
            }
            stack.pop();
            for(int i=top.getList().size()-1; i>=0; i--) {
                stack.push(top.getList().get(i));
            }
        }
        return false;
    }
}