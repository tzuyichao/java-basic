package segment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ForwardMaximumMatchingSegmentation {
    private static final String DICT_COLUMN_SEPARATOR = " ";
    private static final int SEGMENT_WINDOW = 16;
    private static final String DEFAULT_STOP_WORDS_PATH = "/Users/tzuyichao/lab/data/segment/stop_words.txt";
    private static final String DEFAULT_DICTIONARY_PATH = "/Users/tzuyichao/lab/data/segment/dict.txt";
    private static final Logger logger = LoggerFactory.getLogger("Lab");

    private Set<String> stopWords = new HashSet<>();
    private Map<String, Term> dictionary = new HashMap<>();

    private void initStopWords(String stopWordsFilePath) {
        try {
            stopWords.addAll(Files.readAllLines(Paths.get(stopWordsFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<Term> makeTerm(String configRow) {
        String[] termEntry = configRow.split(DICT_COLUMN_SEPARATOR);
        if(termEntry.length == 1) {
            return Optional.of(new Term(termEntry[0]));
        } else if(termEntry.length == 3) {
            return Optional.of(new Term(termEntry[0], Integer.parseInt(termEntry[1]), termEntry[2]));
        } else {
            // format error
            logger.error(String.format("format error: %s", configRow));
            return Optional.empty();
        }
    }

    private void initDictionary(String dictionaryFilePath) {
        try {
            List<String> items = Files.readAllLines(Paths.get(dictionaryFilePath));
            items.stream().forEach(item -> {
                Optional<Term> termResult = makeTerm(item);
                if(termResult.isPresent()) {
                    Term term = termResult.get();
                    dictionary.put(term.getContent(), term);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ForwardMaximumMatchingSegmentation() {
        initStopWords(DEFAULT_STOP_WORDS_PATH);
        initDictionary(DEFAULT_DICTIONARY_PATH);
    }

    public Set<String> getStopWords() {
        return new HashSet<>(stopWords);
    }

    public Map<String, Term> getDictionary() {
        return new HashMap<>(dictionary);
    }

    public List<SegmentToken> process(final String sentence) {
        Objects.requireNonNull(sentence, "sentence should not be null");
        int window = (sentence.length() > SEGMENT_WINDOW?SEGMENT_WINDOW:sentence.length());
        logger.debug("window: {}, sentence len: {}", window, sentence.length());
        int currentPos = 0;
        List<SegmentToken> tokens = new ArrayList<>();
        while(currentPos < sentence.length()) {
            logger.debug("remain sentence: {}", sentence.substring(currentPos));
            boolean findToken = false;
            int forwardIndex = (currentPos + window > sentence.length()?sentence.length()-1:currentPos + window-1);
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
                        tokens.add(new SegmentToken(candidate, currentPos, currentPos+candidate.length()-1));
                        currentPos += candidate.length();
                        findToken = true;
                    } else {
                        forwardIndex--;
                    }
                }
            }
//
//            boolean findMatch = false;
//            for(int testLength=window-1; (testLength > 0 || findMatch == false); testLength--) {
//                if(testLength == 0) {
//                    if(processSentence.length() == 1) {
//                        String part = processSentence.substring(0);
//                        tokens.add(new SegmentToken(part, currentPos, currentPos + part.length()));
//                        processSentence = "";
//                    } else {
//                        String part = processSentence.substring(0, 1);
//                        tokens.add(new SegmentToken(part, currentPos, currentPos + part.length()));
//                        currentPos += part.length();
//                        processSentence = sentence.substring(currentPos);
//                        findMatch = true;
//                        window = (processSentence.length() > SEGMENT_WINDOW ? SEGMENT_WINDOW : processSentence.length());
//                    }
//                } else {
//                    String part = processSentence.substring(0, testLength);
//                    System.out.println(String.format("currentPos: %d, part: %s", currentPos, part));
//                    if (dictionary.keySet().contains(part)) {
//                        tokens.add(new SegmentToken(part, currentPos, currentPos + part.length()));
//                        System.out.println(String.format("%s got", part));
//                        currentPos += part.length();
//                        processSentence = sentence.substring(currentPos);
//                        findMatch = true;
//                        window = (processSentence.length() > SEGMENT_WINDOW ? SEGMENT_WINDOW : processSentence.length());
//                    }
//                }
//            }
        }
        return tokens;
    }
}
