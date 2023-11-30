package com.lsh.vivo.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * 手机号校验器
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-11-22 21:49
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private String regex;

    private boolean nullable;

    @Override
    public void initialize(Mobile constraintAnnotation) {
        regex = constraintAnnotation.regex();
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(charSequence)) {
            return nullable;
        }
        return charSequence.matches(regex);
    }
}
