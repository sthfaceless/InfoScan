package ru.spaceouter.infoscan.validation.validators;

import ru.spaceouter.infoscan.validation.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author danil
 * @date 25.04.19
 */
public class EmailValidator implements ConstraintValidator<Email, Object> {

    private final Pattern email = Pattern.compile("^[A-z|\\d|_]{3,32}@[A-z|.]+$");

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null) return false;
        return email.matcher(o.toString().trim()).matches();
    }
}
