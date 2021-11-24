package org.acme.quickstart.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {JvmLanguageValidator.class})
public @interface JvmLanguage {
    String message() default "You need to provide a Jvm based-language";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
