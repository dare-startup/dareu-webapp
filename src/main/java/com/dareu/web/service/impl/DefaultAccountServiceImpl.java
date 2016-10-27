package com.dareu.web.service.impl;

import com.dareu.web.service.DefaultAccountService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DefaultAccountServiceImpl implements DefaultAccountService{

    @Override
    public ModelAndView defaultView() {
        return new ModelAndView("index"); 
    }

    @Override
    public ModelAndView signinView() {
        return new ModelAndView("signin"); 
    }

    @Override
    public ModelAndView signupView() {
        return new ModelAndView("signup"); 
    }
    
}
