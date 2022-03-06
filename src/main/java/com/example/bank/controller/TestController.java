package com.example.bank.controller;


import com.example.bank.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
//@ResponseBody
@RequestMapping("/test")

public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/mive/narengi")
    //@RequestMapping(method = {RequestMethod.GET,RequestMethod.PUT}, path = "/havij")
    public String writeNameGet(@RequestParam(defaultValue = "sdsd") String name) {
        testService.test();
        System.out.println("Hello " + name);
        return "Hi(:";
    }

    @PostMapping(value = "/havij")
    public ResponseEntity writeName(@RequestBody  String name) {
        if (name == null){
            //throw exception
            return  new ResponseEntity<Integer>(2,HttpStatus.BAD_GATEWAY);
        }else {
            return new ResponseEntity<String>(name,HttpStatus.OK);
        }
        //System.out.println("Hello " + name);
        //return "Hi(: khoshgele!!!!!!";
        //return ResponseEntity<String>(HttpStatus.OK);
    }


}
