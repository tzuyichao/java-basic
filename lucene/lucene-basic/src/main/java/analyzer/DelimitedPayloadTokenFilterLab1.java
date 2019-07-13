package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.payloads.DelimitedPayloadTokenFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PayloadAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DelimitedPayloadTokenFilterLab1 {
    private static void dumpTokenStreamInfo(TokenStream tokenStream) throws IOException {
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        PayloadAttribute payloadAttribute = tokenStream.addAttribute(PayloadAttribute.class);
//        PositionLengthAttribute positionLengthAttribute = tokenStream.addAttribute(PositionLengthAttribute.class);
//        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            System.out.println("[" + charTermAttribute.toString() + ", payload=" + payloadAttribute.toString() + "]");
        }
    }

    public static void main(String[] args) throws IOException {
        // default value
        Map<String, String> delimitedPayloadFilterParam = new HashMap<>();
        delimitedPayloadFilterParam.put("delimiter", "|");
        delimitedPayloadFilterParam.put("encoder", "float");

        Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer("standard")
                .addTokenFilter("lowercase")
                .addTokenFilter(DelimitedPayloadTokenFilterFactory.class, delimitedPayloadFilterParam)
                .build();
        try (TokenStream tokenStream = analyzer.tokenStream("content", "the|1 quick|2.0 fox|3")) {
            dumpTokenStreamInfo(tokenStream);
        } finally {
            analyzer.close();
        }
    }
}
