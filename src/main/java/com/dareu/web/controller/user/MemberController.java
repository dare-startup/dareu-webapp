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
    
    @RequestMapping(value = { "/index", "/" })
    public ModelAndView defaultView(){
        return new ModelAndView("/user/index"); 
    }
    
    @RequestMapping(value = { "/dares" })
    public ModelAndView daresView(){
        return new ModelAndView("/user/dares"); 
    }
    
    @RequestMapping(value = { "/discover" })
    public ModelAndView discoverView(){
        return new ModelAndView("/user/discover"); 
    }
    
    @RequestMapping(value = { "/profile" })
    public ModelAndView profileView(){
        return new ModelAndView("/user/profile"); 
    }
}
