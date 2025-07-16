package com.example.signupemailcertified.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.mapping.Constraint;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    // 특수문자 1개 이상, 8~20자
    private static final String PASSWORD_PATTERN = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,20}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;
        return password.matches(PASSWORD_PATTERN);
    }


}
