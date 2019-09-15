package com.parent.validation.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CnpValidator.class)
@Documented
public @interface CnpConstraint {

    String message() default "CNP incorect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
