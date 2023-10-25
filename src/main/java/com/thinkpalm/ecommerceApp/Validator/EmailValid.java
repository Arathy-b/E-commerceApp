package com.thinkpalm.ecommerceApp.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = EmailValidation.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)

public @interface EmailValid {
    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
