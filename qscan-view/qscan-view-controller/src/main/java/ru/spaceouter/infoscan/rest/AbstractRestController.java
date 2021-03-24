package ru.spaceouter.infoscan.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author danil
 * @date 20.04.19
 */
public abstract class AbstractRestController {

    protected ResponseEntity<?> ok(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected ResponseEntity<?> created(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    protected ResponseEntity<?> accepted(){
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    protected <T> ResponseEntity<T>  found(T obj){
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}
