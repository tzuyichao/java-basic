package segment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * modify from journey
 */
public class SegmentEvaluator {
    private static Logger logger = LoggerFactory.getLogger(SegmentEvaluator.class);
    public static void execute(List<String> answerlists, List<String> predlists) {
        int matchedTokenCount = 0;
        int predTokenCount = 0;
        int answerTokenCount = 0;

        for(int i=0; i<answerlists.size(); i++){
            logger.debug("answer: {}", answerlists.get(i));
            logger.debug("pred: {}", predlists.get(i));
            logger.debug("answertokens({}): {}", i, answerlists.get(i));
            logger.debug("predlists({}): {}", i, predlists.get(i));
            String[] answertokens = answerlists.get(i).trim().split(" ");
            String[] predtokens = predlists.get(i).trim().split(" ");
            logger.debug("answertokens: {}", answertokens.length);
            logger.debug("predtokens: {}", predtokens.length);
            answerTokenCount += answertokens.length;
            predTokenCount += predtokens.length;

            List<String> answerlist = Arrays.asList(answertokens);
            for(String w : predtokens){
                if(answerlist.contains(w)){
                    //logger.debug("{} hit", w);
                    matchedTokenCount++;
                }
            }
        }

        double precision = (double) matchedTokenCount / (double) predTokenCount;
        double recall = (double) matchedTokenCount / answerTokenCount;
        double fscore = 2*precision*recall/ (precision + recall);

        System.out.printf("matched:%d, predCount:%d, answerCount:%d \n", matchedTokenCount, predTokenCount, answerTokenCount);
        System.out.print(String.format("p:%f, r:%f, f:%f", precision, recall, fscore));
    }
}

