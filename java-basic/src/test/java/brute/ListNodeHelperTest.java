package brute;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ListNodeHelperTest {
    @Test
    public void simple1() {
        int[] data = new int[] {4,1,8,4,5};
        ListNode listNode = ListNodeHelper.make(data);
        assertNotNull(listNode);
        int i = 0;
        while(listNode.next != null) {
            assertEquals(data[i], listNode.val);
            i++;
            listNode = listNode.next;
        }
    }
}
