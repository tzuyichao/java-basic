package segment;

import org.junit.Test;
import org.nd4j.linalg.api.ops.impl.transforms.Reverse;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ForwardMaximumMatchingSegmentationTest {

    @Test
    public void test_stopWords() {
        ForwardMaximumMatchingSegmentation segmenter = new ForwardMaximumMatchingSegmentation();
        for(String stopWord : segmenter.getStopWords()) {
            System.out.println(stopWord);
        }
    }

    @Test
    public void test_dump_dictionary() {
        ForwardMaximumMatchingSegmentation segmenter = new ForwardMaximumMatchingSegmentation();
//        segmenter.getDictionary().forEach((term, detail) -> {
//            System.out.println(detail);
//        });
        System.out.println(segmenter.getDictionary().keySet().stream().max(Comparator.comparingInt(String::length)).get().length());
    }

    @Test
    public void test_process() {
        ForwardMaximumMatchingSegmentation segmenter = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmenter.process("我们在野生动物园玩");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_process_long_sentence() {
        ForwardMaximumMatchingSegmentation segmenter = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmenter.process("Akka是一种高度可扩展的软件，这不仅仅表现在性能方面，也表现在它所适用的应用的大小。");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process() {
        ReverseDirectionMaximumMatchingSegmentation segmenter = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmenter.process("我们在野生动物园玩");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process_long_sentence() {
        ReverseDirectionMaximumMatchingSegmentation segmenter = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmenter.process("Akka是一种高度可扩展的软件，这不仅仅表现在性能方面，也表现在它所适用的应用的大小。");
        assertNotNull(tokens);
        System.out.println(tokens);
    }
}
