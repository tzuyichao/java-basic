package org.acme.quickstart.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class JvmLanguageValidator implements ConstraintValidator<JvmLanguage, String> {
    private List<String> favoriteLanguages = List.of("java", "groovy", "scala", "kotlin", "cljure");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return favoriteLanguages.stream().anyMatch(l -> l.equalsIgnoreCase(s));
    }
}
