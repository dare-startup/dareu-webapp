package com.dareu.web.service;

import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.security.DareuUserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView configurationView(Model model); 
    
    /**
     * returns a users view
     * @return 
     */
    public ModelAndView usersView(int pageNumber); 
    
    /**
     * returns a dares view 
     * @return 
     */
    public ModelAndView daresView(); 

    /**
     * creates a new category 
     * @param category
     * @param atts
     * @return 
     */
    public String createCategory(CreateCategoryRequest category, RedirectAttributes atts);
}
