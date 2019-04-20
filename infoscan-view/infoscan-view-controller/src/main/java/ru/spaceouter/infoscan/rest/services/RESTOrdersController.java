package ru.spaceouter.infoscan.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/orders")
public class RESTOrdersController extends AbstractRestController{

    @GetMapping
    public ResponseEntity<?> getOrders(){

        return found();
    }

    @GetMapping
    @RequestMapping(path = "{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") String id){

        return found();
    }

    @PostMapping
    public ResponseEntity<?> createOrder(){

        return created();
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(){

        return accepted();
    }

}
