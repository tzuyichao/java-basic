package segment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ForwardMaximumMatchingSegmentation extends MaximumMatchingSegmentation {
    private static final Logger logger = LoggerFactory.getLogger(ForwardMaximumMatchingSegmentation.class);

    public ForwardMaximumMatchingSegmentation() {
        super();
    }

    public ForwardMaximumMatchingSegmentation(String dicPath, String stopWordsPath) {
        super(dicPath, stopWordsPath);
    }

    @Override
    public List<SegmentToken> process(final String sentence) {
        Objects.requireNonNull(sentence, "sentence should not be null");
        int window = (sentence.length() > SEGMENT_WINDOW?SEGMENT_WINDOW:sentence.length());
        logger.debug("window: {}, sentence len: {}", window, sentence.length());
        int currentPos = 0;
        List<SegmentToken> tokens = new ArrayList<>();
        while(currentPos < sentence.length()) {
            logger.debug("remain sentence: {}", sentence.substring(currentPos));
            boolean findToken = false;
            int forwardIndex = (currentPos + window > sentence.length()?sentence.length():currentPos + window);
            logger.debug("forwardIndex: {}, currentPos: {}", forwardIndex, currentPos);
            while(!findToken) {
                if(forwardIndex == currentPos) {
                    String candidate = sentence.substring(currentPos, forwardIndex+1);
                    tokens.add(new SegmentToken(candidate, currentPos, currentPos+candidate.length()-1));
                    currentPos += 1;
                    findToken = true;
                } else {
                    String candidate = sentence.substring(currentPos, forwardIndex);
                    if(dictionary.keySet().contains(candidate)) {
                        logger.debug("dic hit: {}", candidate);
                        tokens.add(new SegmentToken(candidate, currentPos, currentPos+candidate.length()-1));
                        currentPos += candidate.length();
                        findToken = true;
                    } else {
                        logger.debug("dic miss: {}", candidate);
                        forwardIndex--;
                    }
                }
            }
        }
        return removeStopWords(tokens);
    }
}
