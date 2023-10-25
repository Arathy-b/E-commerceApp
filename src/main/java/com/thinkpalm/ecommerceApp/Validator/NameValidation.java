package com.thinkpalm.ecommerceApp.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidation implements ConstraintValidator<NameValid,String> {
    private static final int min_size=3;
    private static final int max_size=10;


    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        String names=name.trim();
        if(names.isBlank()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Please provide a valid username").addConstraintViolation();
            return false;
        }
        int length=name.length();
        return length>=min_size && length<=max_size;

     }
}

