package brute;

import java.util.Stack;

/**
 * 1209. Remove All Adjacent Duplicates in String II
 *
 * Runtime: 74 ms, faster than 39.75% of Java online submissions for Remove All Adjacent Duplicates in String II.
 * Memory Usage: 54.4 MB, less than 21.88% of Java online submissions for Remove All Adjacent Duplicates in String II.
 *
 * Ref: https://zxi.mytechroad.com/blog/stack/leetcode-1209-remove-all-adjacent-duplicates-in-string-ii/
 */
public class RemoveAllAdjacentDuplicatesInStringII {
    static class Pair<T, S> {
        T v1;
        S v2;
        Pair(T v1, S v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public String removeDuplicates(String s, int k) {
        Stack<Pair<Character, Integer>> stack = new Stack<>();
        stack.push(new Pair('*', 0));
        for(char ch: s.toCharArray()) {
            if(ch != stack.peek().v1) {
                stack.push(new Pair(ch, 1));
            } else if(stack.peek().v2 == k-1) {
                stack.pop();
            } else {
                stack.peek().v2 += 1;
            }
        }

        StringBuilder res = new StringBuilder();
        for(Pair<Character, Integer> pair: stack) {
            if(pair.v1 != '*') {
                for (int i = 0; i < pair.v2; i++) {
                    res.append(pair.v1);
                }
            }
        }
        return res.toString();
    }
}
