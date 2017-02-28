package com.dareu.web.service;

import com.dareu.web.dto.request.ContactRequest;
import com.dareu.web.dto.request.SignupRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.exception.DareuWebApplicationException;
import javax.servlet.http.HttpServletResponse;
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
    
    /**
     * download current android mobile version of dareu
     * @param response
     * @throws DareuWebApplicationException 
     */
    public void downloadAndroidMobileApplication(HttpServletResponse response)throws DareuWebApplicationException; 

    /**
     * Return a contact view
     * @return 
     */
    public ModelAndView contactView(EntityRegistrationResponse registrationResponse);

    /**
     * Saves a contact message 
     * @param request
     * @param atts
     * @return 
     */
    public String contactMessage(ContactRequest request, RedirectAttributes atts);

    /**
     * Return an about view
     * @return 
     */
    public ModelAndView aboutView();
}
