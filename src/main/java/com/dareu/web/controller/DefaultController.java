package com.dareu.web.controller;

import com.dareu.data.request.SigninRequest;
import com.dareu.data.request.SignupRequest;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class DefaultController {
    
    @RequestMapping({ "/index", "/" })
    public ModelAndView defaultView(){
        return new ModelAndView("index"); 
    }
    
    @RequestMapping({ "signin", "login" })
    public ModelAndView signinView(){
        return null; 
    }
    
    @RequestMapping(value = { "signin", "login" }, method = RequestMethod.POST)
    public ModelAndView signin(@ModelAttribute("signinModel")SigninRequest request, 
            RedirectAttributes redirect){
        return null; 
    }
    
    @RequestMapping(value = { "signup", "register" })
    public ModelAndView signupView(){
        return null; 
    }
    
    @RequestMapping(value = { "signup", "register" }, method = RequestMethod.POST)
    public ModelAndView signupView(@ModelAttribute("signupModel")SignupRequest request, 
            RedirectAttributes redirect){
        return null; 
    }
    
    @RequestMapping(value = { "about", "aboutus" })
    public ModelAndView aboutView(){
        return null; 
    }
}
