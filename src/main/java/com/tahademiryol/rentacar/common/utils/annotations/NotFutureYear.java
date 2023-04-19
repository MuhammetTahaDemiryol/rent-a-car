package com.tahademiryol.rentacar.common.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFutureYearValidator.class)
public @interface NotFutureYear {
    String message() default "The year value cannot be in the future";

    //? Different user groups can have different rules and features
    Class<?>[] groups() default {};

    //? used in data transfer objects for carrying messages for different user groups
    Class<? extends Payload>[] payload() default {};
}
