package ru.spaceouter.infoscan.services;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author danil
 * @date 24.04.19
 */
@Service
public class ParametersValidator {

    /*
    * Digit pattern
    * */
    private final Pattern number = Pattern.compile("^\\d+$");

    /*
    * Email pattern
    * */
    private final Pattern email = Pattern.compile("^[A-z|\\-|\\d|_]+@[A-z|.]+$");

    public boolean validateNumber(String str){
        if(str == null || str.equals(""))
            return false;

        return number.matcher(str).matches();
    }

    public boolean validateEmail(String str){
        if(str == null || str.equals(""))
            return false;

        return email.matcher(str).matches();
    }

}
