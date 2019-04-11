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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeMain {
    private static final Logger logger = LoggerFactory.getLogger("Lab");
    public static final String PATH_SEPARATOR = "/";
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/path.csv");
        List<String> allPaths = Files.readAllLines(path);
//
//        Graph graph = new Graph(1013);
//        allPaths.stream()
//                .forEach(org_path -> {
//                    String[] nodesStr = org_path.substring(1).split(PATH_SEPARATOR);
//                    Integer[] nodes = Stream.of(nodesStr).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[0]);
//                    for(int i=0; i<nodes.length-1; i++) {
//                        graph.addEdge(nodes[i], nodes[i+1]);
//                    }
//                });
//        graph.print();
        Map<String, TreeNode> forest = new HashMap<>();
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

        logger.info("Forest Count: {}", forest.keySet().size());
        forest.keySet().forEach(key -> {
            forest.get(key).find("50").setMarked(true);
            forest.get(key).find("126").setMarked(true);
            forest.get(key).find("137").setMarked(true);
            logger.info(forest.get(key).toString());
            logger.info("50 is marked: {}", forest.get(key).find("50").isMarked());
            logger.info("126 is marked: {}", forest.get(key).find("126").isMarked());
            logger.info("137 is marked: {}", forest.get(key).find("137").isMarked());
            TreeNode[] subTreeRoots = forest.get(key).findMarkTransition();
            for(TreeNode node: subTreeRoots) {
                logger.info("node: {}", node.getId());
            }
        });
    }
}
