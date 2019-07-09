package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.Iterator;

/**
 * 來源: https://blog.csdn.net/xxpsw/article/details/78902312
 */
public class TestSmartCN {
    private static void print(String text, Analyzer analyzer) throws IOException {
        TokenStream tokenStream = analyzer.tokenStream("content", text);
        CharTermAttribute attribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while(tokenStream.incrementToken()) {
            System.out.println(attribute.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        String text = "Lucene自带多种分词器，其中对中文分词支持比较好的是smartcn。但smartcn不支援自订词库。上平車這是自訂詞典的詞。";
        //String text = "\"speech recognition\"";
        System.out.println("Raw Query String: " + text);
        System.out.println("Standard Analyzer:");
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        print(text, standardAnalyzer);

        System.out.println("Smart Chinese Analyzer:");
        SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
        print(text, smartChineseAnalyzer);

        System.out.println("ik-Analyzer:");
        Analyzer ikAnalyzer = new IKAnalyzer(true);
        try (TokenStream tokenStream = ikAnalyzer.tokenStream("testField", text)) {
            CharTermAttribute term = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while(tokenStream.incrementToken()) {
                System.out.println(term.toString());
            }
            tokenStream.end();
        }

        System.out.println("Smart Chinese Analyzer with Custom Stop words:");
        CharArraySet charArraySet = new CharArraySet(0, true);
        Iterator<Object> iterator = SmartChineseAnalyzer.getDefaultStopSet().iterator();
        while(iterator.hasNext()) {
            charArraySet.add(iterator.next());
        }
        String[] myStopWords = { "对", "的", "是", "其中" };
        for(String stopWord: myStopWords) {
            charArraySet.add(stopWord);
        }
        SmartChineseAnalyzer smartChineseAnalyzer_withCustomStopWords = new SmartChineseAnalyzer(charArraySet);
        print(text, smartChineseAnalyzer_withCustomStopWords);
    }
}
