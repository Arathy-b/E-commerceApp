package com.thinkpalm.ecommerceApp.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = NameValidation.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValid {
    String message() default "Username must contain atleast 3 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
