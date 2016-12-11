package com.dareu.web.controller.admin;

import com.dareu.web.dto.request.CreateCategoryRequest;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    
    @Autowired
    private AdminService service; 
    
    @RequestMapping(value = { "/index", "/" })
    public ModelAndView defaultView(){
        return service.defaultView(); 
    }
    
    @RequestMapping(value = { "/users" })
    public ModelAndView usersView(@RequestParam(required = false, name = "pageNumber", defaultValue = "1")int pageNumber){
        return service.usersView(pageNumber); 
    }
    
    @RequestMapping(value = { "/dares" })
    public ModelAndView daresView(){
        return service.daresView();
    }
    
    @RequestMapping(value = { "/configuration" })
    public ModelAndView configurationView(Model model){
        return service.configurationView(model); 
    }
    
    @RequestMapping(value = { "/dare/category" }, method = RequestMethod.POST)
    public String createCategory(@ModelAttribute("category")CreateCategoryRequest category, RedirectAttributes atts){
        return service.createCategory(category, atts);
    }
    
    
}
