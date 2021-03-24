package ru.sthfaceless.qscan.validation.validators;

import ru.spaceouter.infoscan.validation.Username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author danil
 * @date 25.04.19
 */
public class UsernameValidator implements ConstraintValidator<Username, Object> {

    private final Pattern username = Pattern.compile("^[A-z|\\d|_]{5,32}$");

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null) return false;
        return username.matcher(o.toString().trim()).matches();
    }
}
