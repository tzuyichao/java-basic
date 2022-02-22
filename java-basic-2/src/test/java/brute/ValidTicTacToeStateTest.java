package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidTicTacToeStateTest {
    @Test
    public void happy_1() {
        var solver = new ValidTicTacToeState();
        assertThat(solver.validTicTacToe(new String[] {"O  ","   ","   "}), is(false));
    }

    @Test
    public void happy_2() {
        var solver = new ValidTicTacToeState();
        assertThat(solver.validTicTacToe(new String[] {"XOX"," X ","   "}), is(false));
    }

    @Test
    public void happy_3() {
        var solver = new ValidTicTacToeState();
        assertThat(solver.validTicTacToe(new String[] {"XOX","O O","XOX"}), is(true));
    }

    @Test
    public void happy_4() {
        var solver = new ValidTicTacToeState();
        assertThat(solver.validTicTacToe(new String[] {"XXX","   ","OOO"}), is(false));
    }

    @Test
    public void happy_5() {
        var solver = new ValidTicTacToeState();
        assertThat(solver.validTicTacToe(new String[] {"XXX","   ","OO "}), is(true));
    }
}
