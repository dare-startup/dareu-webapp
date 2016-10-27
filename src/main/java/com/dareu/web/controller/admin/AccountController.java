package com.dareu.web.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
@RequestMapping("admin")
public class AccountController {
    @RequestMapping("/")
    public ModelAndView defaultView(){
        return new ModelAndView("index");
    }
}
