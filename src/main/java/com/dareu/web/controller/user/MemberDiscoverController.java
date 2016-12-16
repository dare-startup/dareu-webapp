package com.dareu.web.controller.user;

import com.dareu.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@Controller
@RequestMapping("member/discover")
public class MemberDiscoverController {
    
    
    @Autowired
    private MemberService memberService; 
    
    @RequestMapping("users")
    public ModelAndView discoverUsersView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts){
        return memberService.discoverUsersView(pageNumber, atts); 
    }
    
    @RequestMapping("dares")
    public ModelAndView discoverDaresView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts){
        return memberService.discoverDaresView(pageNumber, atts); 
    }
    
    @RequestMapping("responses")
    public ModelAndView discoverResponsesView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts){
        return memberService.discoverResponsesView(pageNumber, atts); 
    }
    
    @RequestMapping("trending")
    public ModelAndView discoverTrendingView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts){
        return memberService.discoverTrendingView(pageNumber, atts); 
    }
    
    @RequestMapping("sponsors")
    public ModelAndView discoverSponsorView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts){
        return memberService.discoverSponsorsView(pageNumber, atts); 
    }
}
