package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.payloads.DelimitedPayloadTokenFilterFactory;
import org.apache.lucene.analysis.payloads.PayloadHelper;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PayloadAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import static org.apache.lucene.analysis.payloads.DelimitedPayloadTokenFilterFactory.ENCODER_ATTR;

public class DelimitedPayloadTokenFilterLab1 {
    private static void dumpTokenStreamInfo(TokenStream tokenStream) throws IOException {
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        PayloadAttribute payloadAttribute = tokenStream.addAttribute(PayloadAttribute.class);
//        PositionLengthAttribute positionLengthAttribute = tokenStream.addAttribute(PositionLengthAttribute.class);
//        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            BytesRef payload =payloadAttribute.getPayload();
            // https://stackoverflow.com/questions/47087884/lucene-convert-bytesref-to-float
            float payload_content_from_stackoverflow = ByteBuffer.wrap(payload.bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
            // 看source覺得用這個比較直覺
            float payload_content2 = PayloadHelper.decodeFloat(payload.bytes);
            System.out.println("[" + charTermAttribute.toString() + ", payload=" + payload_content2 + "]");
        }
    }

    public static void main(String[] args) throws IOException {
        // default value
        Map<String, String> delimitedPayloadFilterParam = new HashMap<>();
//        delimitedPayloadFilterParam.put(DELIMITER_ATTR, "|");
        delimitedPayloadFilterParam.put(ENCODER_ATTR, "float");

        Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer(WhitespaceTokenizerFactory.class)
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
