package segment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReverseDirectionMaximumMatchingSegmentation extends MaximumMatchingSegmentation {
    private static final Logger logger = LoggerFactory.getLogger(ReverseDirectionMaximumMatchingSegmentation.class);

    public ReverseDirectionMaximumMatchingSegmentation() {
        super();
    }

    public ReverseDirectionMaximumMatchingSegmentation(String dicPath, String stopWordsPath) {
        super(dicPath, stopWordsPath);
    }

    @Override
    public List<SegmentToken> process(final String sentence) {
        Objects.requireNonNull(sentence, "sentence should not be null");
        String sent = sentence.toLowerCase();
        int window = (sent.length() > SEGMENT_WINDOW?SEGMENT_WINDOW:sent.length());
        int reverseIndex = sent.length();
        int currentPos = sent.length() - window;
        logger.debug("window: {}, currentPos: {}, reverseIndex: {}, sentence len: {}", window, currentPos, reverseIndex, sent.length());
        List<SegmentToken> tokens = new ArrayList<>();
        while(reverseIndex > 0) {
            logger.debug("remain sentence: {}, currentPos: {}, reverseIndex: {}", sent.substring(currentPos, reverseIndex), currentPos, reverseIndex);
            boolean findToken = false;
            while(!findToken) {
                if(currentPos == reverseIndex-1) {
                    String candidate = sent.substring(currentPos, reverseIndex);
                    tokens.add(new SegmentToken(candidate, currentPos, currentPos+candidate.length()-1));
                    reverseIndex -= candidate.length();
                    currentPos = reverseIndex > SEGMENT_WINDOW?reverseIndex-SEGMENT_WINDOW:0;
                    findToken = true;
                } else {
                    String candidate = sent.substring(currentPos, reverseIndex);
                    if(dictionary.keySet().contains(candidate)) {
                        logger.debug("dic hit: {}", candidate);
                        tokens.add(new SegmentToken(dictionary.get(candidate).getContent(), currentPos, currentPos+candidate.length()-1));
                        reverseIndex -= candidate.length();
                        currentPos = reverseIndex > SEGMENT_WINDOW?reverseIndex-SEGMENT_WINDOW:0;
                        findToken = true;
                    } else {
                        logger.debug("dic miss: {}", candidate);
                        currentPos += 1;
                    }
                }
            }
        }
        return removeStopWords(tokens);
    }
}
