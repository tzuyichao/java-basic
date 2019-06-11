package tiny;

import java.util.LinkedList;
import java.util.Queue;

public class TinyEvaluator extends tiny.TinyBaseListener {
    private Queue<Integer> values = new LinkedList<>();

    @Override
    public void enterProgram(tiny.TinyParser.ProgramContext ctx) {
        System.out.println(String.format("Enter Program: %s", ctx));
    }

    @Override
    public void exitWrite_stmt(tiny.TinyParser.Write_stmtContext ctx) {
        System.out.println(String.format("Exit Write: %s", ctx.expr_list()));
        while(!values.isEmpty()) {
            System.out.println(values.poll());
        }
    }

    @Override
    public void exitExpr_list(tiny.TinyParser.Expr_listContext ctx) {
        super.exitExpr_list(ctx);
    }

    @Override
    public void exitIdent(tiny.TinyParser.IdentContext ctx) {
        System.out.println(String.format("Exit Ident: %s", ctx.ID()));
        values.offer(0);
    }

    @Override
    public void exitExpr(tiny.TinyParser.ExprContext ctx) {
        System.out.println(String.format("Exit Expr: [expr: %s, op: %s, factor: %s]", ctx.expr(), ctx.op(), ctx.factor()));
        if(ctx.op() != null) {
            System.out.println(String.format("Exit Expr - 'op' text: %s", ctx.op().getText()));
        }
        if(ctx.expr() != null) {
            System.out.println(String.format("Exit Expr - 'expr' text: %s", ctx.expr().getText()));
        }
        if(ctx.factor() != null) {
            System.out.println(String.format("Exit Expr - 'factor' text: %s", ctx.factor().getText()));
        }
    }

    @Override
    public void exitOp(tiny.TinyParser.OpContext ctx) {
        System.out.println(String.format("Exit Op: %s", ctx.getText()));
    }
}
