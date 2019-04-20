package ru.spaceouter.infoscan.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/support")
public class RESTSupportController extends AbstractRestController{

    @PostMapping
    public ResponseEntity<?> createQuestion(){

        return created();
    }


}
