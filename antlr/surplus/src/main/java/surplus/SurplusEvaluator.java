package surplus;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SurplusEvaluator extends surplus.SurplusBaseListener {
    private Deque<String> current = new ConcurrentLinkedDeque<>();

//    @Override
//    public void enterNotExpression(surplus.SurplusParser.NotExpressionContext context) {
//        System.out.println("enterNotExpression: " + context.getText());
//    }

    @Override
    public void exitNotExpression(surplus.SurplusParser.NotExpressionContext context) {
        System.out.println("exitNotExpression: " + context.getText());
        System.out.println("-" + current);
    }

    @Override
    public void enterPhraseExpression(surplus.SurplusParser.PhraseExpressionContext context) {
        current.push(context.getText());
        System.out.println("enterPhraseExpression: " + context.getText());
    }

    @Override
    public void enterTermExpression(surplus.SurplusParser.TermExpressionContext context) {
        current.push(context.getText());
        System.out.println("enterTermExpression: " + context.getText());
    }

    @Override
    public void enterAndExpression(surplus.SurplusParser.AndExpressionContext context) {
        System.out.println("enterAndExpression: " + context.getText());
        System.out.println("content: " + current);
    }

    @Override
    public void exitAndExpression(surplus.SurplusParser.AndExpressionContext context) {
        System.out.println("exitAndExpression: " + context.getText());
        System.out.println("content: " + current);
    }

    @Override
    public void enterExpressionExpression(surplus.SurplusParser.ExpressionExpressionContext context) {
        System.out.println("enterExpressionExpression");
    }

    @Override
    public void exitExpressionExpression(surplus.SurplusParser.ExpressionExpressionContext context) {
        System.out.println("exitExpressionExpression");
    }
}
