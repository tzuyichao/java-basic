package brute;

import java.util.function.BiConsumer;

import static org.junit.Assert.assertEquals;

public class ListNodeHelper {
    public static ListNode make(int[] data, ListNode tail) {
        if(null == data || data.length == 0) {
            return tail;
        }
        ListNode result = make(data);
        result.next = tail;
        return result;
    }

    public static ListNode make(int[] data) {
        if(null == data || data.length == 0) {
            return null;
        }
        ListNode root = new ListNode(data[0]);
        ListNode current = root;
        for(int i=1; i<data.length; i++) {
            current.next = new ListNode(data[i]);
            current = current.next;
        }

        return root;
    }

    public static void assertListNode(int[] expect, ListNode listNode) {
        int i = 0;
        while(listNode != null) {
            assertEquals(expect[i], listNode.val);
            i++;
            listNode = listNode.next;
        }
    }

    public static boolean isEqualsTo(int[] expect, ListNode listNode, BiConsumer<Integer, Integer> notMatchCallback) {
        int i = 0;
        while(listNode != null) {
            if(expect[i] != listNode.val) {
                if(notMatchCallback != null) {
                    notMatchCallback.accept(expect[i], listNode.val);
                }
                return false;
            }
            i++;
            listNode = listNode.next;
        }
        return true;
    }

    public static boolean isEqualsTo(int[][] expect, ListNode[] listNodes) {
        if(expect.length != listNodes.length) {
            System.out.println("length miss match");
            return false;
        }
        for(int i=0; i<expect.length; i++) {
            return isEqualsTo(expect[i], listNodes[i], (expected, actual) -> {
                System.out.println(String.format("Expect %d, but got %d", expected, actual));
            });
        }
        return true;
    }
}
