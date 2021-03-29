package core;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.junit.Test;

import java.io.IOException;

public class TokenFilterTest extends BaseTokenStreamTestCase {
    @Test
    public void testInvalidOffsets() throws IOException {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName) {
                Tokenizer tokenizer = new MockTokenizer(MockTokenizer.WHITESPACE, false);
                TokenFilter filters = new ASCIIFoldingFilter(tokenizer);
                filters = new NGramTokenFilter(filters, 2, 2, false);
                return new TokenStreamComponents(tokenizer, filters);
            }
        };
        assertAnalyzesTo(analyzer, "mosfellsbær",
                new String[] { "mo", "os", "sf", "fe", "el", "ll", "ls", "sb", "ba", "ae", "er" },
                new int[]    {    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0 },
                new int[]    {   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,   11 },
                new int[]    {     1,   0,    0,    0,    0,    0,    0,    0,    0,    0,    0  });
        analyzer.close();
    }
}
