package calc;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends calc.CalcBaseVisitor<Double> {
    private Map<String, Double> vars = new HashMap<>();

    @Override
    public Double visitAssign(calc.CalcParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Double val = visit(ctx.expr());
        vars.put(id, val);
        return val;
    }

    @Override
    public Double visitPrintExpr(calc.CalcParser.PrintExprContext ctx) {
        Double val = visit(ctx.expr());
        System.out.println(val);
        return .0;
    }

    @Override
    public Double visitLiteral(calc.CalcParser.LiteralContext ctx) {
        return Double.valueOf(ctx.NUMBER().getText());
    }

    @Override
    public Double visitId(calc.CalcParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if(vars.containsKey(id)) {
            return vars.get(id);
        }
        return .0;
    }

    @Override
    public Double visitMulDiv(calc.CalcParser.MulDivContext ctx) {
        double lhs = visit(ctx.expr(0));
        double rhs = visit(ctx.expr(1));
        if(ctx.op.getType() == calc.CalcParser.MUL) {
            return lhs * rhs;
        }
        return lhs/rhs;
    }

    @Override
    public Double visitAddSub(calc.CalcParser.AddSubContext ctx) {
        double lhs = visit(ctx.expr(0));
        double rhs = visit(ctx.expr(1));
        if(ctx.op.getType() == calc.CalcParser.ADD) {
            return lhs + rhs;
        }
        return lhs - rhs;
    }

    @Override
    public Double visitParen(calc.CalcParser.ParenContext ctx) {
        return visit(ctx.expr());
    }

}
