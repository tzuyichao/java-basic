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
    public List<SegmentToken> process(String sentence) {
        Objects.requireNonNull(sentence, "sentence should not be null");
        int window = (sentence.length() > SEGMENT_WINDOW?SEGMENT_WINDOW:sentence.length());
        int reverseIndex = sentence.length();
        int currentPos = sentence.length() - window;
        logger.debug("window: {}, currentPos: {}, reverseIndex: {}, sentence len: {}", window, currentPos, reverseIndex, sentence.length());
        List<SegmentToken> tokens = new ArrayList<>();
        while(reverseIndex > 0) {
            logger.debug("remain sentence: {}, currentPos: {}, reverseIndex: {}", sentence.substring(currentPos, reverseIndex), currentPos, reverseIndex);
            boolean findToken = false;
            while(!findToken) {
                if(currentPos == reverseIndex-1) {
                    String candidate = sentence.substring(currentPos, reverseIndex);
                    tokens.add(new SegmentToken(candidate, currentPos, currentPos+candidate.length()-1));
                    reverseIndex -= candidate.length();
                    currentPos = reverseIndex > SEGMENT_WINDOW?reverseIndex-SEGMENT_WINDOW:0;
                    findToken = true;
                } else {
                    String candidate = sentence.substring(currentPos, reverseIndex);
                    if(dictionary.keySet().contains(candidate)) {
                        logger.debug("dic hit: {}", candidate);
                        tokens.add(new SegmentToken(candidate, currentPos, currentPos+candidate.length()-1));
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
