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
    public ModelAndView defaultView(RedirectAttributes atts); 
    
    /**
     * returns a configuration view 
     * @param model
     * @param pageNumber
     * @return 
     */
    public ModelAndView configurationView(Model model, int pageNumber, RedirectAttributes atts); 
    
    /**
     * returns a users view
     * @param pageNumber
     * @return 
     */
    public ModelAndView usersView(int pageNumber, RedirectAttributes atts); 
    
    /**
     * returns a dares view 
     * @param pageNumber
     * @return 
     */
    public ModelAndView daresView(int pageNumber, RedirectAttributes atts); 

    /**
     * creates a new category 
     * @param category
     * @param atts
     * @return 
     */
    public String createCategory(CreateCategoryRequest category, RedirectAttributes atts);
}
