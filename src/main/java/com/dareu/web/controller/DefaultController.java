package com.dareu.web.controller;

import com.dareu.web.dto.request.SignupRequest;
import com.dareu.web.exception.DareuWebApplicationException;
import com.dareu.web.service.DefaultAccountService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Controller
public class DefaultController {
    
    @Autowired
    private DefaultAccountService service; 
    
    @RequestMapping({ "/index", "/" })
    public ModelAndView defaultView(){
        return service.defaultView();
    }
    
    @RequestMapping({ "/signin", "/login" })
    public ModelAndView signinView(){
        return service.signinView();
    }
    
    @RequestMapping(value = { "/signup", "/register" }, method = RequestMethod.GET)
    public String signupView(Model model){
        return service.signupView(model);
    }
    
    @RequestMapping(value = { "/signup", "/register" }, method = RequestMethod.POST)
    public String signupView(@ModelAttribute("signup")SignupRequest request, 
            RedirectAttributes redirect){
        return service.signupRequest(request, redirect); 
    }
    
    @RequestMapping(value = { "/about", "/aboutus" })
    public ModelAndView aboutView(){
        return null; 
    }
    
    @RequestMapping(value = { "/android" })
    public void downloadApkFile(HttpServletResponse response)throws DareuWebApplicationException{
        service.downloadAndroidMobileApplication(response);
    }
    
    @RequestMapping(value = { "/contact" })
    public ModelAndView contactView(){
        return service.contactView(); 
    }
}
