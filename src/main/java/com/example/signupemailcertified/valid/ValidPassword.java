package com.example.signupemailcertified.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "비밀번호는 8~20자이며, 특수문자를 1개 이상 포함해야합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
