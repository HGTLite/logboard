package com.hgt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * README:
 * Created by Yao on 12/7/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class TESTController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
