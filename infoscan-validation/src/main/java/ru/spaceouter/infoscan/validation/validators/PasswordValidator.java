package ru.spaceouter.infoscan.validation.validators;

import ru.spaceouter.infoscan.validation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author danil
 * @date 25.04.19
 */
public class PasswordValidator implements ConstraintValidator<Password, Object> {

    private final Pattern password = Pattern.compile("^[A-z|А-я|ё|Ё|\\d|_]{5,32}$");

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null) return false;
        return password.matcher(o.toString().trim()).matches();
    }
}
