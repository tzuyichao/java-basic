package lambda;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class ExecuteAroundPattern {
    public static double read(ScannerDoubleFunction snf) throws IOException  {
        try(Scanner scanner = new Scanner(Path.of("doubles.txt"), StandardCharsets.UTF_8)) {
            return snf.readDouble(scanner);
        }
    }

    private static double getFirst(Scanner scanner) {
        if(scanner.hasNextDouble()) {
            return scanner.nextDouble();
        }
        return Double.NaN;
    }

    private static double sumAll(Scanner scanner) {
        double sum = 0.0d;

        while(scanner.hasNextDouble()) {
            sum += scanner.nextDouble();
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        double singleDouble = ExecuteAroundPattern.read((Scanner sc) -> getFirst(sc));
        double sumAllDoubles = ExecuteAroundPattern.read(sc -> sumAll(sc));
        System.out.println(singleDouble);
        System.out.println(sumAllDoubles);
    }
}
