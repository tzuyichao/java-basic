package brute;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class PathSumTest {

    @Test
    public void simple1() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("PathSumTest.txt");

        String content = new String(inputStream.readAllBytes());
        String[] all = content.split("\\r\\n");

        for(int i=0; i<all.length/3; i++) {
            PathSum solver = new PathSum();
            String treeString = all[i*3];
            String sumString = all[i*3+1];
            String expectString = all[i*3+2];
            Integer[] data = null;
            if(!treeString.trim().equals("[]")) {
                String[] treeArray = treeString.trim().replace("[", "").replace("]", "").split(",");
                data = new Integer[treeArray.length];
                for (int idx = 0; idx < treeArray.length; idx++) {
                    if (treeArray[idx].equals("null")) {
                        data[idx] = null;
                    } else {
                        data[idx] = Integer.valueOf(treeArray[idx]);
                    }
                }
            }
            TreeNode tree = TreeNodeHelper.make(data);
            System.out.println(treeString);
            assertEquals(Boolean.parseBoolean(expectString.trim()), solver.hasPathSum(tree, Integer.parseInt(sumString)));
        }
    }
}
