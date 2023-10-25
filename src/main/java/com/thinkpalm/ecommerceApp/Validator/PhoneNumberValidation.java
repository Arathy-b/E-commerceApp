package com.thinkpalm.ecommerceApp.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

public class PhoneNumberValidation implements ConstraintValidator<PhoneNumbervalid,String> {
    @Override
    public boolean isValid(String ph, ConstraintValidatorContext constraintValidatorContext) {

        if (ph == null) {
            return false;
        }
        return ph.matches("^[0-9]{10}$");
    }
}
