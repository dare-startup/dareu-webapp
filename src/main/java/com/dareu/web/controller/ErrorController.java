package com.dareu.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jose.rubalcaba
 */
@RequestMapping("error")
public class ErrorController {

    @RequestMapping(value = "404", method = RequestMethod.GET)
    public String notFound() {
        return "not-found";
    }
}
