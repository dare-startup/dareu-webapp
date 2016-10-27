package com.dareu.web.service;

import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
public interface DefaultAccountService {
    
    /**
     * Get a default root view
     * @return 
     */
    public ModelAndView defaultView(); 
    
    /**
     * Get the signin view
     * @return 
     */
    public ModelAndView signinView(); 
    
    /**
     * Get the signup view
     * @return 
     */
    public ModelAndView signupView(); 
}
