package ru.spaceouter.infoscan.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/coins")
public class RESTCoinsController extends AbstractRestController{

    @GetMapping
    public ResponseEntity<?> getCoins(){

        return found();
    }

    @GetMapping
    @RequestMapping(path = "/history")
    public ResponseEntity<?> getPaymentHistory(){

        return found();
    }

    @PostMapping
    public ResponseEntity<?> createPaymentRequest(){

        return accepted();
    }

}
