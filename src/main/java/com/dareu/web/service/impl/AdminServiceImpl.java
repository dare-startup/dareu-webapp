package com.dareu.web.service.impl;

import com.dareu.web.conn.AdminMethodName;
import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AdminService;
import com.dareu.web.service.ApacheConnectorService;
import com.dareu.web.service.JsonParserService;
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
public class AdminServiceImpl implements AdminService{
    
    private Logger log = Logger.getLogger("AdminService"); 
    
    @Autowired
    private ApacheConnectorService connector; 
    
    @Autowired
    private JsonParserService jsonParser; 
    
    public AdminServiceImpl(){
        
    }

    @Override
    public ModelAndView defaultView() {
        return new ModelAndView("admin/index"); 
    }

    @Override
    public ModelAndView configurationView(Model model) {
        //creates a view model 
        ModelAndView mav = new ModelAndView("admin/configuration"); 
        try{
            //get categories 
            String categoriesContextPath = AdminMethodName.CATEGORIES.toString();
            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(categoriesContextPath);
            Type type = new TypeToken<Page<CategoryDescription>>(){}.getType(); 
            switch(wrapper.getStatusCode()){
                case 200: 
                    Page<CategoryDescription> categories = jsonParser
                            .<Page<CategoryDescription>>parseJson(wrapper.getJsonResponse(), type);
                    mav.addObject("categories", categories); 
                    model.addAttribute("category", new CreateCategoryRequest());
                    break; 
                case 404: 
                    break; 
                case 500: 
                     break; 
            }
            
            //create a new category 
            mav.addObject("category", new CreateCategoryRequest());
            return mav; 
        } catch(IOException ex){
            log.severe("Could not connect to DareU api: " + ex.getMessage());
        } catch(Exception ex){
            log.severe("Exception: " + ex.getMessage());
        }
        return mav;
    }

    @Override
    public ModelAndView usersView(int pageNumber) {
        ModelAndView mav = new ModelAndView("admin/users"); 
        String contextPath = String.format(AdminMethodName.USERS_BY_PAGE.toString(), pageNumber); 
        //get a list of users
        try{
            ApacheResponseWrapper wrapper = connector.performSuperAdminGetOperation(contextPath);
            switch(wrapper.getStatusCode()){
                case 200: 
                    //create a page 
                    Page<UserAccount> account = jsonParser.parseJson(wrapper.getJsonResponse(), 
                            new TypeToken<Page<UserAccount>>(){}.getType());
                    mav.addObject("users", account);
                    break;
                case 404: 
                    //log.info();
                    break; 
                case 500: 
                    break;
            }
        }catch(IOException ex){
            
        }
        return mav; 
    }

    @Override
    public ModelAndView daresView() {
        return new ModelAndView("admin/dares"); 
    }

    @Override
    public String createCategory(CreateCategoryRequest category, RedirectAttributes atts) {
        //create the new category
        //create a category description 
        String newCategoryCxtPath = AdminMethodName.NEW_CATEGORY.toString();
        try{
            ApacheResponseWrapper wrapper = connector.performSuperAdminPostOperation(newCategoryCxtPath, category);
            switch(wrapper.getStatusCode()){
                case 200: 
                    EntityRegistrationResponse response = jsonParser
                            .parseJson(wrapper.getJsonResponse(), EntityRegistrationResponse.class); 
                    log.info(String.format("Registered a new %s with id %s", response.getRegistrationType(), response.getId()));
                    break; 
                case 500: 
                    break; 
                case 404: 
                    break; 
            }
        }catch(IOException ex){
            
        }
        return "redirect:/admin/configuration";
    }
    
}
