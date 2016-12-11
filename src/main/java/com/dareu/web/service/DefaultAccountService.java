package com.dareu.web.service;

import com.dareu.web.dto.request.SignupRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * @param model
     * @return 
     */
    public String signupView(Model model); 
    
    /**
     * Register a user signup
     * @param request
     * @param atts
     * @return 
     */
    public String signupRequest(SignupRequest request, RedirectAttributes atts);
}
