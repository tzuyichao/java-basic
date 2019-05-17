package word2vec;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

public class TestText8 {
    public static void main(String[] args) {
        Word2Vec word2Vec = WordVectorSerializer.readWord2VecModel("/Users/tzuyichao/lab/python/basic/text8.bin");
        double similarity = word2Vec.similarity("woman", "man");
        System.out.println(similarity);
    }
}
