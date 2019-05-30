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
        String sent = sentence.toLowerCase();
        int window = (sent.length() > SEGMENT_WINDOW?SEGMENT_WINDOW:sent.length());
        logger.debug("window: {}, sentence len: {}", window, sent.length());
        int currentPos = 0;
        List<SegmentToken> tokens = new ArrayList<>();
        while(currentPos < sent.length()) {
            logger.debug("remain sentence: {}", sent.substring(currentPos));
            boolean findToken = false;
            int forwardIndex = (currentPos + window > sent.length()?sent.length():currentPos + window);
            logger.debug("forwardIndex: {}, currentPos: {}", forwardIndex, currentPos);
            while(!findToken) {
                if(forwardIndex == currentPos) {
                    String candidate = sent.substring(currentPos, forwardIndex+1);
                    tokens.add(new SegmentToken(candidate, currentPos, currentPos+candidate.length()-1));
                    currentPos += 1;
                    findToken = true;
                } else {
                    String candidate = sent.substring(currentPos, forwardIndex);
                    if(dictionary.keySet().contains(candidate)) {
                        logger.debug("dic hit: {}", candidate);
                        tokens.add(new SegmentToken(dictionary.get(candidate).getContent(), currentPos, currentPos+candidate.length()-1));
                        currentPos += candidate.length();
                        findToken = true;
                    } else {
                        logger.debug("dic miss: {}", candidate);
                        forwardIndex--;
                    }
                }
            }
        }
        if(isFilterStopWords()) {
            return removeStopWords(tokens);
        } else {
            return tokens;
        }
    }
}
