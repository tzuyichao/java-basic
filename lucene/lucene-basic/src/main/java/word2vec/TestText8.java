package word2vec;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

/**
 * 這個程式的目的是測試gensim訓練出來的模型，能不能在java透過dl4j讀進來做預測
 */
public class TestText8 {
    public static void main(String[] args) {
        Word2Vec word2Vec = WordVectorSerializer.readWord2VecModel("/Users/tzuyichao/lab/python/basic/text8.bin");
        double similarity = word2Vec.similarity("woman", "man");
        System.out.println(similarity);
    }
}
