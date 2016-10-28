package com.dareu.web.controller.admin;

import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView usersView(){
        return service.usersView(); 
    }
    
    @RequestMapping(value = { "/dares" })
    public ModelAndView daresView(){
        return service.daresView();
    }
    
    @RequestMapping(value = { "/configuration" })
    public ModelAndView configurationView(@AuthenticationPrincipal DareuUserDetails details){
        return service.configurationView(details); 
    }
}
