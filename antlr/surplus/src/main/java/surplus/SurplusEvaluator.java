package surplus;

public class SurplusEvaluator extends surplus.SurplusBaseListener {

    @Override
    public void enterNotExpression(surplus.SurplusParser.NotExpressionContext context) {
        System.out.println("enterNotExpression: " + context.getText());
    }

    @Override
    public void enterPhraseExpression(surplus.SurplusParser.PhraseExpressionContext context) {
        System.out.println("enterPhraseExpression: " + context.getText());
    }

    @Override
    public void enterTermExpression(surplus.SurplusParser.TermExpressionContext context) {
        System.out.println("enterTermExpression: " + context.getText());
    }
}
