package segment;

public class SegmentToken {
    private String word;
    private int startOffset;
    private int endOffset;

    public SegmentToken(String word, int startOffset, int endOffset) {
        this.word = word;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public String getWord() {
        return word;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    @Override
    public String toString() {
        return "SegmentToken{" +
                "word='" + word + '\'' +
                ", startOffset=" + startOffset +
                ", endOffset=" + endOffset +
                '}';
    }
}
