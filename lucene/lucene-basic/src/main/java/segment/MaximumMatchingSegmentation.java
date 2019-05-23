package segment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public abstract class MaximumMatchingSegmentation {
    protected static final String DICT_COLUMN_SEPARATOR = " ";
    protected static final String DEFAULT_STOP_WORDS_PATH = "/Users/tzuyichao/lab/data/segment/stop_words.txt";
    protected static final String DEFAULT_DICTIONARY_PATH = "/Users/tzuyichao/lab/data/segment/dict.big.txt";
    private static final Logger logger = LoggerFactory.getLogger(MaximumMatchingSegmentation.class);

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

    public MaximumMatchingSegmentation() {
        initStopWords(DEFAULT_STOP_WORDS_PATH);
        initDictionary(DEFAULT_DICTIONARY_PATH);
        SEGMENT_WINDOW = 16;
    }

    public MaximumMatchingSegmentation(String dicPath, String stopWordsPath) {
        initDictionary(dicPath);
        initStopWords(stopWordsPath);
        SEGMENT_WINDOW = dictionary.keySet().stream().max(Comparator.comparingInt(String::length)).get().length();
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

    public abstract List<SegmentToken> process(final String sentence);
}
