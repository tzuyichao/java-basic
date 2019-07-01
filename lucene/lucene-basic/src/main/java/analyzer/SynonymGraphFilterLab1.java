package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.FlattenGraphFilterFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Synonym Filter has been Deprecated
 */
public class SynonymGraphFilterLab1 {
    private static void dumpTokenStreamInfo(TokenStream tokenStream) throws IOException {
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        PositionLengthAttribute positionLengthAttribute = tokenStream.addAttribute(PositionLengthAttribute.class);
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            System.out.println("[" + charTermAttribute.toString() + ": pl=" + positionLengthAttribute.getPositionLength() + ", offset=" + offsetAttribute.startOffset() + ", " + offsetAttribute.endOffset() + "]");
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> synonymGraphFilterFactoryParams1 = new HashMap<>();
        synonymGraphFilterFactoryParams1.put("synonyms", "mysynonyms.txt");

        Map<String, String> synonymGraphFilterFactoryParams2 = new HashMap<>();
        synonymGraphFilterFactoryParams2.put("synonyms", "mysynonyms.txt");

        Map<String, String> flattenGraphFilterFactoryParams = new HashMap<>();

        Analyzer analyzer1 = CustomAnalyzer.builder()
                .withTokenizer("standard")
                .addTokenFilter("lowercase")
                .addTokenFilter(SynonymGraphFilterFactory.class, synonymGraphFilterFactoryParams1)
                .build();
        // FlattenGraphFilter
        // WARNING: This API is experimental and might change in incompatible ways in the next release.
        Analyzer analyzer2 = CustomAnalyzer.builder()
                .withTokenizer("standard")
                .addTokenFilter("lowercase")
                .addTokenFilter(SynonymGraphFilterFactory.class, synonymGraphFilterFactoryParams2)
                .addTokenFilter(FlattenGraphFilterFactory.class, flattenGraphFilterFactoryParams)
                .build();

        System.out.println("SynonymGraphFilterFactory without flatten graph filter");

        try (TokenStream tokenStream = analyzer1.tokenStream("content", "weeny teh small couch")) {
            dumpTokenStreamInfo(tokenStream);
        } finally {
            analyzer1.close();
        }

        System.out.println("SynonymGraphFilterFactory with flatten graph filter");

        try (TokenStream tokenStream = analyzer2.tokenStream("content", "weeny teh small couch")) {
            dumpTokenStreamInfo(tokenStream);
        } finally {
            analyzer2.close();
        }
    }
}
