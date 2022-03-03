package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class EvaluateReversePolishNotationTest {

    @Test
    public void happy1() {
        var solver = new EvaluateReversePolishNotation();
        assertThat(solver.evalRPN(new String[] {"2","1","+","3","*"}), is(equalTo(9)));
    }

    @Test
    public void happy2() {
        var solver = new EvaluateReversePolishNotation();
        assertThat(solver.evalRPN(new String[] {"4","13","5","/","+"}), is(equalTo(6)));
    }

    @Test
    public void happy3() {
        var solver = new EvaluateReversePolishNotation();
        assertThat(solver.evalRPN(new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"}), is(equalTo(22)));
    }
}
