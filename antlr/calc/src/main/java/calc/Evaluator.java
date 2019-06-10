package calc;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.HashMap;
import java.util.Map;

public class Evaluator extends calc.CalcBaseListener {
    private Map<String, Double> vars = new HashMap<>();
    private ParseTreeProperty<Double> values = new ParseTreeProperty<>();

    @Override
    public void exitAssign(calc.CalcParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Double val = values.get(ctx.expr());
        vars.put(id, val);
    }

    @Override
    public void exitPrintExpr(calc.CalcParser.PrintExprContext ctx) {
        System.out.println(values.get(ctx.expr()));
    }

    @Override
    public void exitLiteral(calc.CalcParser.LiteralContext ctx) {
        values.put(ctx, Double.valueOf(ctx.NUMBER().getText()));
    }

    @Override
    public void exitId(calc.CalcParser.IdContext ctx) {
        values.put(ctx, vars.containsKey(ctx.ID().getText())?vars.get(ctx.ID().getText()):.0);
    }

    @Override
    public void exitMulDiv(calc.CalcParser.MulDivContext ctx) {
        double lhs = values.get(ctx.expr(0));
        double rhs = values.get(ctx.expr(1));
        values.put(ctx, ctx.op.getType() == calc.CalcParser.MUL?lhs*rhs:lhs/rhs);
    }

    @Override
    public void exitAddSub(calc.CalcParser.AddSubContext ctx) {
        double lhs = values.get(ctx.expr(0));
        double rhs = values.get(ctx.expr(1));
        values.put(ctx, ctx.op.getType() == calc.CalcParser.ADD?lhs+rhs:lhs-rhs);
    }

    @Override
    public void exitParen(calc.CalcParser.ParenContext ctx) {
        values.put(ctx, values.get(ctx.expr()));
    }
}
