package com.thinkpalm.ecommerceApp.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = PhoneNumberValidation.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface  PhoneNumbervalid {
    String message() default "Invalid phone number format. Please provide a 10-digit integer phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
