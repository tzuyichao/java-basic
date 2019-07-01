package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.shingle.ShingleFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAnalyzerLab1 {
    public static void main(String[] args) throws IOException {
        Map<String, String> shingleFilterFactoryParams = new HashMap<>();
        Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer("standard")
                .addTokenFilter("lowercase")
                .addTokenFilter(ShingleFilterFactory.class, shingleFilterFactoryParams)
                .build();

        TokenStream tokenStream = analyzer.tokenStream("content", "To be, or what?");
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }
        analyzer.close();
    }
}
