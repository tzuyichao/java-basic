package analyzer;

import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

public class TestNGramTokenizer {
    public static void main(String[] args) throws IOException {
        NGramTokenizer nGramTokenizer = new NGramTokenizer(1, 2);
        nGramTokenizer.setReader(new StringReader("飯後請先等個半小時再刷牙"));
        nGramTokenizer.addAttribute(CharTermAttribute.class);
        nGramTokenizer.reset();
        while(nGramTokenizer.incrementToken()) {
            CharTermAttribute charTermAttribute = nGramTokenizer.getAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute.toString());
        }
        nGramTokenizer.end();
    }
}
