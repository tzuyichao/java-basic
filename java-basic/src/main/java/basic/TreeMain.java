package basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TreeMain {
    private static final Logger logger = LoggerFactory.getLogger("Lab");
    public static final String PATH_SEPARATOR = "/";
    public static void main(String[] args) {
        Path path = Paths.get("src/main/resources/path.csv");
        Map<String, TreeNode> forest = new HashMap<>();
        try {
            List<String> allPaths = Files.readAllLines(path);
            logger.debug("line size: {}", allPaths.size());
//            allPaths = allPaths.stream().filter(line -> line != null && !"".equals(line.trim())).collect(Collectors.toList());
//            logger.info("line size: {}", allPaths.size());
            allPaths.stream()
                    .forEach(org_path -> {
                        String[] nodes = org_path.substring(1).split(PATH_SEPARATOR);
                        logger.debug("{}: {}", org_path, nodes.length);
                        for(int i=0; i<nodes.length; i++) {
                            if(i == 0) {
                                if(!forest.containsKey(nodes[i])) {
                                    forest.put(nodes[i], new TreeNode(nodes[i]));
                                }
                            } else {
                                TreeNode root = forest.get(nodes[0]);
                                root.add(nodes[i-1], nodes[i]);
                            }
                        }
                    });
        } catch(IOException e) {
            e.printStackTrace();
        }
        logger.info("Forest Count: {}", forest.keySet().size());
        forest.keySet().forEach(key -> {
            logger.info(forest.get(key).toString());
        });
    }
}
