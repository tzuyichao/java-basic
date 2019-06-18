package scoring;

import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.ClassicSimilarity;

public class MyTestSimilarity extends ClassicSimilarity {
    public MyTestSimilarity() {
        super();
        this.discountOverlaps = true;
    }

    @Override
    public float lengthNorm(int numTerms) {
        return (float) 1.0/numTerms;
        //return super.lengthNorm(numTerms);
    }

    @Override
    public float tf(float freq) {
        return super.tf(freq);
    }

    @Override
    public float idf(long docFreq, long docCount) {
        return super.idf(docFreq, docCount);
    }

    @Override
    public Explanation idfExplain(CollectionStatistics collectionStats, TermStatistics termStats) {
        return super.idfExplain(collectionStats, termStats);
    }
}
