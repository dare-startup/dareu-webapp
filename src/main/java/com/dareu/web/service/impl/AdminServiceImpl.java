package com.dareu.web.service.impl;

import com.dareu.web.conn.AdminMethodName;
import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AdminService;
import com.dareu.web.conn.ApacheConnectorService;
import com.dareu.web.conn.cxt.JsonParserService;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class AdminServiceImpl implements AdminService {

    private Logger log = Logger.getLogger("AdminService");

    @Autowired
    private ConnectorManager connectorManager;

    public AdminServiceImpl() {

    }

    @Override
    public ModelAndView defaultView(RedirectAttributes atts) {
        return new ModelAndView("admin/index");
    }

    @Override
    public ModelAndView configurationView(Model model, int pageNumber, RedirectAttributes atts) {
        //creates a view model 
        ModelAndView mav = new ModelAndView("admin/configuration");
        try {
            Page<CategoryDescription> categories = connectorManager.getCategories(pageNumber);
            mav.addObject("categories", categories);
            model.addAttribute("category", new CreateCategoryRequest());
            //create a new category 
            mav.addObject("category", new CreateCategoryRequest());
            return mav;
        } catch (ConnectorManagerException ex) {
            mav.setViewName("");
            log.severe("Exception: " + ex.getMessage());
        }
        return mav;
    }

    @Override
    public ModelAndView usersView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("admin/users");
        try {
            Page<UserAccount> page = connectorManager.getUsersByPage(pageNumber);
            mav.addObject("users", page);
        } catch (ConnectorManagerException ex) {
            //redirect to error via attributes
            atts.addFlashAttribute("error", 
                    new ApplicationError(ex.getMessage(), "/admin/dare/category")); 
            mav.setViewName("/error/appError");
        }
        return mav;
    }

    @Override
    public ModelAndView daresView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("admin/dares");
        try {
            Page<DareDescription> page = connectorManager.getUnapprovedDares(pageNumber);
            mav.addObject("dares", page);
        } catch (ConnectorManagerException ex) {
            //redirect to error via attributes
            atts.addFlashAttribute("error", 
                    new ApplicationError("There has been an error, please try again", "/admin/dare/category")); 
            mav.setViewName("/error/appError");
        }
        return mav;
    }

    @Override
    public String createCategory(CreateCategoryRequest category, RedirectAttributes atts) {
        //create the new category
        try{
            EntityRegistrationResponse response = connectorManager.createCategory(category); 
            return "redirect:/admin/configuration";
        }catch(ConnectorManagerException ex){
            //redirect to error via attributes
            atts.addFlashAttribute("error", 
                    new ApplicationError("There has been an error creating the category, please try again", "/admin/dare/category")); 
            return "redirect:/error/appError"; 
        }
    }

}
