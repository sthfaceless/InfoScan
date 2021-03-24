package ru.spaceouter.infoscan.validation.validators;

import ru.spaceouter.infoscan.validation.Link;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author danil
 * @date 25.04.19
 */
public class LinkValidator implements ConstraintValidator<Link, Object> {

    private final Pattern link = Pattern.compile("^[A-z|\\d|_]+$");

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null) return false;
        return link.matcher(o.toString().trim()).matches();
    }
}
