package lambda.loan;

import java.io.IOException;

public class FormulaLab {
    public static void main(String[] args) throws IOException {
        double xPlusYMinusZ = Formula.compute(f -> f.add().add().minus().result());
        System.out.println(xPlusYMinusZ);
    }
}
