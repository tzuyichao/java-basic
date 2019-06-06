package segment;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class SegmentToken {
    private String word;
    private int startOffset;
    private int endOffset;
    private boolean isDictionaryTerm;

    public SegmentToken(String word, int startOffset, int endOffset, boolean isDictionaryTerm) {
        this.word = word;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.isDictionaryTerm = isDictionaryTerm;
    }
}
