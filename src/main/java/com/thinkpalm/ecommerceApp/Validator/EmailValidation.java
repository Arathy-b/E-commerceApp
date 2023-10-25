package com.thinkpalm.ecommerceApp.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidation implements ConstraintValidator<EmailValid,String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String emails=email.trim();
        if(emails.isBlank()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Email shouldnt be empty").addConstraintViolation();
            return false;
        }
        return emails.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }
}
