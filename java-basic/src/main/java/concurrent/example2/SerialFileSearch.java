package concurrent.example2;

import java.io.File;

public class SerialFileSearch {
    public static void searchFiles(File file, String filename, Result result) {
        File[] contents = file.listFiles();
        if((null == contents) || contents.length == 0) {
            return;
        }
        for(File content: contents) {
            if(content.isDirectory()) {
                searchFiles(content, filename, result);
            } else {
                if(content.getName().equals(filename)) {
                    result.setPath(content.getAbsolutePath());
                    result.setFound(true);
                    System.out.printf("Serial Search: Path: %s%n", result.getPath());
                    return;
                }
            }
            if(result.isFound()) {
                return;
            }
        }
    }
}
