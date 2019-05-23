package segment;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class MaximumMatchingSegmentationTest {

    @Test
    public void test_stopWords() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        for(String stopWord : segmentation.getStopWords()) {
            System.out.println(stopWord);
        }
    }

    @Test
    public void test_dump_dictionary() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        segmentation.getDictionary().forEach((term, detail) -> {
            System.out.println(detail);
        });
        System.out.println(segmentation.getDictionary().keySet().stream().max(Comparator.comparingInt(String::length)).get().length());
    }

    @Test
    public void test_process() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("我们在野生动物园玩");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_process_long_sentence() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("Akka是一种高度可扩展的软件，这不仅仅表现在性能方面，也表现在它所适用的应用的大小。");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process() {
        ReverseDirectionMaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("我们在野生动物园玩");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process_long_sentence() {
        ReverseDirectionMaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("Akka是一种高度可扩展的软件，这不仅仅表现在性能方面，也表现在它所适用的应用的大小。");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process2() {
        MaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("研究生命的起源");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_process2() {
        MaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("研究生命的起源");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

}
