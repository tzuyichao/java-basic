package segment;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class SegmentToken {
    private String word;
    private int startOffset;
    private int endOffset;

    public SegmentToken(String word, int startOffset, int endOffset) {
        this.word = word;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }
}
