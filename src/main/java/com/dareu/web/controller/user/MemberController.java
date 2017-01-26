package com.dareu.web.controller.user;

import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView defaultView(){
        return memberService.defaultView();
    }
    
    @RequestMapping(value = { "/hot" })
    public ModelAndView hotView(){
         return memberService.hotView();
    }
    
    @RequestMapping(value = { "/anchored" })
    public ModelAndView anchoredView(){
        return memberService.anchoredView();
    }
    
    @RequestMapping(value = { "/success" })
    public ModelAndView successView(@ModelAttribute(MemberService.SUCCESS_TYPE)String successType, 
            @ModelAttribute(MemberService.REGISTRATION_RESPONSE)EntityRegistrationResponse response){
        return memberService.successView(successType, response);
    }
}
