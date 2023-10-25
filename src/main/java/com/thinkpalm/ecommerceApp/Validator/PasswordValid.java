package com.thinkpalm.ecommerceApp.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = PasswordValidation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {
    String message() default "Password must contain atleast 6 characters, at least one uppercase letter, one lowercase letter and a special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

