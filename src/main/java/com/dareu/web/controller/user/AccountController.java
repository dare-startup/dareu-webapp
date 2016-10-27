package com.dareu.web.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
@RequestMapping("user")
public class AccountController {
    
    @RequestMapping("/")
    public ModelAndView defaultView(){
        return new ModelAndView("index"); 
    }
}
