package com.dareu.web.service.impl;

import com.dareu.web.client.SuperAdminClientService;
import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.service.AdminService;
import com.dareu.web.dto.client.DareClientService;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.service.AbstractService;
import java.io.IOException;
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
public class AdminServiceImpl extends AbstractService implements AdminService {

    private Logger log = Logger.getLogger("AdminService");

    @Autowired
    private DareClientService dareClientService;
    
    @Autowired
    private SuperAdminClientService superAdminClientService;

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
            Page<CategoryDescription> categories = dareClientService.getCategories(pageNumber, getDetails().getToken())
                    .execute()
                    .body();
            mav.addObject(CATEGORIES_REQUEST_ATTRIBUTE, categories);
            model.addAttribute(CATEGORY_REQUEST_ATTRIBUTE, new CreateCategoryRequest());
            //create a new category 
            mav.addObject(CATEGORY_REQUEST_ATTRIBUTE, new CreateCategoryRequest());
            return mav;
        } catch (IOException ex) {
            mav.setViewName("");
            log.severe("Exception: " + ex.getMessage());
        }
        return mav;
    }

    @Override
    public ModelAndView usersView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("admin/users");
        try {
            Page<UserAccount> page = superAdminClientService.findUsers(pageNumber, getDetails().getToken())
                    .execute()
                    .body();
            mav.addObject(USERS_REQUEST_ATTRIBUTE, page);
        } catch (IOException ex) {
            //redirect to error via attributes
            atts.addFlashAttribute(ERROR_REQUEST_ATTRIBUTE, 
                    new ApplicationError(ex.getMessage(), "/admin/dare/category")); 
            mav.setViewName("/error/appError");
        }
        return mav;
    }

    @Override
    public ModelAndView daresView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("admin/dares");
        try {
            Page<DareDescription> page = superAdminClientService.findUnapprovedDares(pageNumber, getDetails().getToken())
                    .execute()
                    .body();
            mav.addObject(DARES_REQUEST_ATTRIBUTE, page);
        } catch (IOException ex) {
            //redirect to error via attributes
            atts.addFlashAttribute(ERROR_REQUEST_ATTRIBUTE, 
                    new ApplicationError("There has been an error, please try again", "/admin/dare/category")); 
            mav.setViewName("/error/appError");
        }
        return mav;
    }

    @Override
    public String createCategory(CreateCategoryRequest category, RedirectAttributes atts) {
        //create the new category
        try{
            EntityRegistrationResponse response = superAdminClientService.createCategory(category, getDetails().getToken())
                    .execute()
                    .body();
            return "redirect:/admin/configuration";
        }catch(IOException ex){
            //redirect to error via attributes
            atts.addFlashAttribute(ERROR_REQUEST_ATTRIBUTE, 
                    new ApplicationError("There has been an error creating the category, please try again", "/admin/dare/category")); 
            return "redirect:/error/appError"; 
        }
    }

}
