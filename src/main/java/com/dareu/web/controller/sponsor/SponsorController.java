package com.dareu.web.controller.sponsor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
@Controller
@RequestMapping("sponsor")
public class SponsorController {
    
    @RequestMapping(value = { "/" })
    public ModelAndView defaultView(){
        return new ModelAndView("/sponsor/index"); 
    }
    
    @RequestMapping(value = { "/dares" })
    public ModelAndView daresView(){
        return new ModelAndView("/sponsor/dares"); 
    }
    
    @RequestMapping(value = { "/souvenirs" })
    public ModelAndView souvenirsView(){
        return new ModelAndView("/sponsor/souvenirs"); 
    }
}
