package com.dareu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jose.rubalcaba
 */
@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping(value = "404", method = RequestMethod.GET)
    public String notFound() {
        return "not-found";
    }
    
    @RequestMapping(value = "unauthorized", method = RequestMethod.GET)
    public String unauthorized() {
        return "unauthorized";
    }
}
