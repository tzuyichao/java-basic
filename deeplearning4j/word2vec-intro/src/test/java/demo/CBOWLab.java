package demo;

import javafx.print.Collation;
import org.deeplearning4j.models.embeddings.learning.impl.elements.CBOW;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.junit.Test;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.IOException;
import java.util.Collection;

/**
 * Example from "Deep Learning for Search"
 */
public class CBOWLab {
    @Test
    public void simple1() throws IOException {
        String filePath = new ClassPathResource("billboard_lyrics_1964-2015.csv").getFile().getAbsolutePath();
        SentenceIterator iterator = new BasicLineIterator(filePath);

        Word2Vec vec = new Word2Vec.Builder()
                .layerSize(100)
                .windowSize(5)
                .iterate(iterator)
                .elementsLearningAlgorithm(new CBOW<>())
                .build();
        vec.fit();

        String[] words = new String[] {"guitar", "love", "rock"};
        for(String w: words) {
            Collection<String> lst = vec.wordsNearest(w, 2);
            System.out.println("2 words closest to " + w + ": " + lst);
        }
    }
}
