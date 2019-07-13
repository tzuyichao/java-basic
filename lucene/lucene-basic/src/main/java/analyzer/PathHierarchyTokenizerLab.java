package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.path.PathHierarchyTokenizerFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;

import java.io.IOException;
public class PathHierarchyTokenizerLab {
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
        Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer(PathHierarchyTokenizerFactory.class)
                .build();
        try (TokenStream tokenStream = analyzer.tokenStream("content", "/Users/tzuyichao/src/neo4j")) {
            dumpTokenStreamInfo(tokenStream);
        } finally {
            analyzer.close();
        }
    }
}
