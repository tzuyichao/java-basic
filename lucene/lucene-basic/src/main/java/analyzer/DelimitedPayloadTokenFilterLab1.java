package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.payloads.DelimitedPayloadTokenFilterFactory;
import org.apache.lucene.analysis.payloads.FloatEncoder;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PayloadAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;
import org.apache.lucene.util.BytesRef;
import org.codehaus.stax2.ri.typed.ValueDecoderFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import static org.apache.lucene.analysis.payloads.DelimitedPayloadTokenFilterFactory.ENCODER_ATTR;

public class DelimitedPayloadTokenFilterLab1 {
    private static void dumpTokenStreamInfo(TokenStream tokenStream) throws IOException {
        ValueDecoderFactory.FloatDecoder floatDecoder = new ValueDecoderFactory.FloatDecoder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        PayloadAttribute payloadAttribute = tokenStream.addAttribute(PayloadAttribute.class);
//        PositionLengthAttribute positionLengthAttribute = tokenStream.addAttribute(PositionLengthAttribute.class);
//        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            BytesRef payload =payloadAttribute.getPayload();
            float payload_content = ByteBuffer.wrap(payload.bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
            System.out.println("[" + charTermAttribute.toString() + ", payload=" + payload_content + "]");
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
