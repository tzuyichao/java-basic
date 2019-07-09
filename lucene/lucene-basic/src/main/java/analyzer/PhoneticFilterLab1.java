package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.phonetic.PhoneticFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhoneticFilterLab1 {
    private static void dumpTokenStreamInfo(TokenStream tokenStream) throws IOException {
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        PositionLengthAttribute positionLengthAttribute = tokenStream.addAttribute(PositionLengthAttribute.class);
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            System.out.println("[" + charTermAttribute.toString() + ": pl=" + positionLengthAttribute.getPositionLength() + ", offset=" + offsetAttribute.startOffset() + ", " + offsetAttribute.endOffset() + "]");
        }
    }

    public static void main(String[] args)throws IOException {
        Map<String, String> phoneticFilterParam = new HashMap<>();
        phoneticFilterParam.put("encoder", "Metaphone");

        Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer("standard")
                .addTokenFilter("lowercase")
                .addTokenFilter(PhoneticFilterFactory.class, phoneticFilterParam)
                .build();

        try (TokenStream tokenStream = analyzer.tokenStream("content", "weeny teh small couch")) {
            dumpTokenStreamInfo(tokenStream);
        } finally {
            analyzer.close();
        }
    }
}
