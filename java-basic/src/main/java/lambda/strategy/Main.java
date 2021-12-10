package lambda.strategy;

public class Main {
    public static void main(String[] args) {
        Validator numericValidator = new Validator(s -> s.matches("\\d+"));
        System.out.println(numericValidator.validate("aaaa"));

        Validator lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(lowerCaseValidator.validate("aaaa"));
    }
}
