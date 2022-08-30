package file;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileUtilTest {
    @Test
    void testFiles() throws IOException {
        var fileUtil = new FileUtil();
        fileUtil.testFiles("/data/c");
    }

    @Test
    void testDefaultFileSystem() {
        var fileUtil = new FileUtil();
        fileUtil.testDefaultFileSystem();
    }

    @Test
    void testNewFileSystem() {
        var fileUtil = new FileUtil();
        fileUtil.testNewFileSystem();
    }
}
