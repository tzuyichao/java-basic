package segment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
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
}
