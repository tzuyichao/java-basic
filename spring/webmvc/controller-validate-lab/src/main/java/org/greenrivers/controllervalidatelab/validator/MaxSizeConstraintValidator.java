package org.greenrivers.controllervalidatelab.validator;

import org.greenrivers.controllervalidatelab.model.Movie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, List<Movie>> {

    @Override
    public void initialize(MaxSizeConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<Movie> movies, ConstraintValidatorContext constraintValidatorContext) {
        return movies.size() <= 4;
    }
}
