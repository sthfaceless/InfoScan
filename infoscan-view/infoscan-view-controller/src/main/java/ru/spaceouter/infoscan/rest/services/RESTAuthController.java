package ru.spaceouter.infoscan.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/auth")
public class RESTAuthController extends AbstractRestController{

    @PostMapping
    public ResponseEntity<?> auth(){

        return ok();
    }

    @PostMapping
    @RequestMapping(path = "/reg")
    public ResponseEntity<?> reg(){

        return created();
    }

    @PutMapping
    @RequestMapping(path = "/reg/{uuid}")
    public ResponseEntity<?> regConfirm(@PathVariable("uuid") String uuid){

        return accepted();
    }

    @PostMapping
    @RequestMapping(path = "/restore")
    public ResponseEntity<?> restore(){

        return created();
    }

    @PutMapping
    @RequestMapping(path = "/restore/{uuid}")
    public ResponseEntity<?> restoreConfirm(@PathVariable("uuid") String uuid){

        return accepted();
    }

    @PostMapping
    @RequestMapping(path = "/logout")
    public ResponseEntity<?> logout(){

        return ok();
    }

}
