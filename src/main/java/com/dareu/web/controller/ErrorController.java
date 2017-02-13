package com.dareu.web.controller;

import com.dareu.web.exception.ApplicationError;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @RequestMapping(value = "appError", method = RequestMethod.GET)
    public ModelAndView unauthorized(@ModelAttribute("error")ApplicationError error) {
        ModelAndView mav = new ModelAndView("application-error");
        
        mav.addObject("errorMessage", error.getErrorMessage()); 
        mav.addObject("redirect", error.getRedirect()); 
        
        return mav; 
    }
}
