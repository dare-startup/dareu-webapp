package com.dareu.web.controller.sponsor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
@RequestMapping("sponsor")
public class AccountController {
    
    @RequestMapping(value = { "index" })
    public ModelAndView defaultView(){
        return new ModelAndView("index"); 
    }
}
