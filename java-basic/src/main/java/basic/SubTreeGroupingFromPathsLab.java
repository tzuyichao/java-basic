package basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SubTreeGroupingFromPathsLab {
    private static final Logger logger = LoggerFactory.getLogger("Lab");
    public static final String PATH_SEPARATOR = "/";

    public static String[] sampling(int size) throws IOException {
        Path path = Paths.get("src/main/resources/path.csv");
        List<String> allPaths = Files.readAllLines(path);
        Random random = new Random(System.currentTimeMillis());
        boolean[] visited = new boolean[allPaths.size()];
        String[] result = new String[size];
        int count = 0;
        while(count < size) {
            int index = random.nextInt(allPaths.size());
            if(visited[index] == false) {
                result[count] = allPaths.get(index);
                count++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String[] sample = sampling(20);
        for(String path: sample) {
            System.out.println(path);
        }
    }
}
