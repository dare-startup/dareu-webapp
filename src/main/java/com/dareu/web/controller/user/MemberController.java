package com.dareu.web.controller.user;

import com.dareu.web.dto.request.ChangeEmailAddressRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.exception.DareuWebApplicationException;
import com.dareu.web.service.AbstractService;
import com.dareu.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping("member")
public class MemberController{
    
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value = { "/" })
    public ModelAndView defaultView() throws DareuWebApplicationException{
        return memberService.defaultView();
    }
    
    @RequestMapping(value = { "/hot" })
    public ModelAndView hotView()throws DareuWebApplicationException{
         return memberService.hotView();
    }
    
    @RequestMapping(value = { "/anchored" })
    public ModelAndView anchoredView()throws DareuWebApplicationException{
        return memberService.anchoredView();
    }
    
    @RequestMapping(value = { "/success" })
    public ModelAndView successView(@ModelAttribute(AbstractService.SUCCESS_TYPE)String successType, 
            @ModelAttribute(AbstractService.REGISTRATION_RESPONSE)EntityRegistrationResponse response){
        return memberService.successView(successType, response);
    }
    
    @RequestMapping(value = "/settings")
    public ModelAndView settingsView()throws DareuWebApplicationException{
        return memberService.settingsView(); 
    }
    
    @RequestMapping(value = "/profile")
    public ModelAndView profileView(@ModelAttribute(AbstractService.MESSAGE_REQUEST_PARAMETER)String message)throws DareuWebApplicationException{
        return memberService.currentUserProfile(message); 
    }
    
    @RequestMapping(value = "/profile/changeEmail", method = RequestMethod.POST)
    public String changeEmailAddress(@ModelAttribute(AbstractService.CHANGE_EMAIL_ADDRESS_MODEL)ChangeEmailAddressRequest request, RedirectAttributes atts){
        return memberService.changeEmailAddress(request, atts); 
    }
}
