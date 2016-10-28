package com.dareu.web.service;

import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
public interface AdminService {
    
    /**
     * returns a default view
     * @return 
     */
    public ModelAndView defaultView(); 
    
    /**
     * returns a configuration view 
     * @return 
     */
    public ModelAndView configurationView(); 
    
    /**
     * returns a users view
     * @return 
     */
    public ModelAndView usersView(); 
    
    /**
     * returns a dares view 
     * @return 
     */
    public ModelAndView daresView(); 
}
