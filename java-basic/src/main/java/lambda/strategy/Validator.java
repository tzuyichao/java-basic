package lambda.strategy;

public class Validator {
    private final ValidationStrategy strategy;
    public Validator(ValidationStrategy s) {
        this.strategy = s ;
    }

    public boolean validate(String subject) {
        return strategy.execute(subject);
    }
}
