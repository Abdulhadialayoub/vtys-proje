package com.rf.coin_analysis.validation;

import com.rf.coin_analysis.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValid implements ConstraintValidator<UniqueEmail,String> {
    private final UserRepository repository;

    public UniqueEmailValid(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !repository.existsByEmail(s);
    }
}
