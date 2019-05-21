package segment;

public class Term {
    private String content;
    private Integer freq;
    private String partOfSpeech;

    public Term(String content) {
        this.content = content;
    }

    public Term(String content, Integer freq, String partOfSpeech) {
        this(content);
        this.freq = freq;
        this.partOfSpeech = partOfSpeech;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    @Override
    public String toString() {
        return "Term{" +
                "content='" + content + '\'' +
                ", freq=" + freq +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                '}';
    }
}
