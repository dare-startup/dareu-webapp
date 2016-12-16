package com.dareu.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
@Controller
@RequestMapping("member")
public class MemberController {
    
    @RequestMapping(value = { "/" })
    public ModelAndView defaultView(){
        return new ModelAndView("user/index"); 
    }
    
    @RequestMapping(value = { "/hot" })
    public ModelAndView daresView(){
        return new ModelAndView("user/hot"); 
    }
    
    @RequestMapping(value = { "/anchored" })
    public ModelAndView anchoredView(){
        return new ModelAndView("user/anchored"); 
    }
}
