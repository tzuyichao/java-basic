package segment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MaximumMatchingSegmentation {
    protected static final String DICT_COLUMN_SEPARATOR = " ";
    protected static final String DEFAULT_STOP_WORDS_PATH = "/Users/tzuyichao/lab/data/segment/stop_words.txt";
    protected static final String DEFAULT_DICTIONARY_PATH = "/Users/tzuyichao/lab/data/segment/dict.big.txt";
    private static final Logger logger = LoggerFactory.getLogger(MaximumMatchingSegmentation.class);

    protected boolean isFilterStopWords = false;
    protected int SEGMENT_WINDOW;
    protected Set<String> stopWords = new HashSet<>();
    protected Map<String, Term> dictionary = new HashMap<>();

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
//            logger.error(String.format("format error: %s", configRow));
//            return Optional.empty();
            return Optional.of(new Term(configRow));
        }
    }

    private void initDictionary(String dictionaryFilePath) {
        try {
            List<String> items = Files.readAllLines(Paths.get(dictionaryFilePath));
            items.stream().forEach(item -> {
                Optional<Term> termResult = makeTerm(item);
                if(termResult.isPresent()) {
                    Term term = termResult.get();
                    dictionary.put(term.getContent().toLowerCase(), term);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MaximumMatchingSegmentation() {
        initStopWords(DEFAULT_STOP_WORDS_PATH);
        initDictionary(DEFAULT_DICTIONARY_PATH);
        SEGMENT_WINDOW = 16;
    }

    public MaximumMatchingSegmentation(String dicPath, String stopWordsPath) {
        initDictionary(dicPath);
        initStopWords(stopWordsPath);
        SEGMENT_WINDOW = dictionary.keySet().stream().max(Comparator.comparingInt(String::length)).get().length();
        logger.info("dic size: {}", dictionary.size());
        logger.info("stop words size: {}", stopWords.size());
    }

    public Set<String> getStopWords() {
        return new HashSet<>(stopWords);
    }

    public Map<String, Term> getDictionary() {
        return new HashMap<>(dictionary);
    }

    public List<SegmentToken> removeStopWords(final List<SegmentToken> tokensNeedFilter) {
        List<SegmentToken> tokens = new ArrayList<>();
        tokensNeedFilter.stream().forEach(segmentToken -> {
            if(!stopWords.contains(segmentToken.getWord())) {
                tokens.add(segmentToken);
            }
        });
        return tokens;
    }

    public boolean isFilterStopWords() {
        return isFilterStopWords;
    }

    public void setIsFilterStopWords(boolean isFilterStopWords) {
        this.isFilterStopWords = isFilterStopWords;
    }

    public List<SegmentToken> postProcessing_remove_space(List<SegmentToken> tokens) {
        logger.debug("before: {}", tokens);
        final String emptySpace = "\\x{a0}";
        List<SegmentToken> processedTokens = new ArrayList<>();
        tokens.forEach(token -> {
            if(!token.getWord().matches(emptySpace) && !token.getWord().equals(" ") && !token.getWord().equals("　") && !token.getWord().equals("\n")) {
                processedTokens.add(token);
            }
        });
        return processedTokens;
    }

    public List<SegmentToken> postProcessing_number(List<SegmentToken> tokens) {
        logger.debug("before: {}", tokens);
        final String checkPattern = "^[0-9]$";
        int currentPos = 0;
        int offset = 1;
        List<SegmentToken> processedTokens = new ArrayList<>();

        while(currentPos < tokens.size()) {
            SegmentToken token = tokens.get(currentPos);
            if(!token.isDictionaryTerm()) {
                if (token.getWord().matches(checkPattern)) {
                    List<SegmentToken> candidates = new ArrayList<>();
                    candidates.add(token);
                    boolean found = false;
                    while (!found) {
                        SegmentToken nextToken = null;
                        if (currentPos + offset < tokens.size()) {
                            nextToken = tokens.get(currentPos + offset);
                        }
                        if (nextToken != null && nextToken.getWord().matches(checkPattern)) {
                            candidates.add(nextToken);
                            offset += 1;
                        } else {
                            currentPos += offset;
                            offset = 1;
                            found = true;
                        }
                    }
                    int start = candidates.get(0).getStartOffset();
                    int end = candidates.get(candidates.size() - 1).getEndOffset();
                    String newTokenTerm = candidates.stream().map(SegmentToken::getWord).collect(Collectors.joining());
                    SegmentToken newToken = new SegmentToken(newTokenTerm, start, end, false);
                    processedTokens.add(newToken);
                } else {
                    processedTokens.add(token);
                    currentPos += 1;
                }
            } else {
                processedTokens.add(token);
                currentPos += 1;
            }
        }
        logger.debug("after: {}", processedTokens);
        return processedTokens;
//        return tokens;
    }

    public List<SegmentToken> postProcessing(List<SegmentToken> tokens) {
        logger.debug("before: {}", tokens);
        final String checkPattern = "^[a-zA-Z-]$";
        int currentPos = 0;
        int offset = 1;
        List<SegmentToken> processedTokens = new ArrayList<>();

        while(currentPos < tokens.size()) {
            SegmentToken token = tokens.get(currentPos);
            if(!token.isDictionaryTerm()) {
                if (token.getWord().matches(checkPattern)) {
                    List<SegmentToken> candidates = new ArrayList<>();
                    candidates.add(token);
                    boolean found = false;
                    while (!found) {
                        SegmentToken nextToken = null;
                        if (currentPos + offset < tokens.size()) {
                            nextToken = tokens.get(currentPos + offset);
                        }
                        if (nextToken != null && nextToken.getWord().matches(checkPattern)) {
                            candidates.add(nextToken);
                            offset += 1;
                        } else {
                            currentPos += offset;
                            offset = 1;
                            found = true;
                        }
                    }
                    int start = candidates.get(0).getStartOffset();
                    int end = candidates.get(candidates.size() - 1).getEndOffset();
                    String newTokenTerm = candidates.stream().map(SegmentToken::getWord).collect(Collectors.joining());
                    SegmentToken newToken = new SegmentToken(newTokenTerm, start, end, false);
                    processedTokens.add(newToken);
                } else {
                    processedTokens.add(token);
                    currentPos += 1;
                }
            } else {
                processedTokens.add(token);
                currentPos += 1;
            }
        }
        logger.debug("after: {}", processedTokens);
        return processedTokens;
//        return tokens;
    }

    public abstract List<SegmentToken> process(final String sentence);
}
