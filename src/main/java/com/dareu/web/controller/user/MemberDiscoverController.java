package com.dareu.web.controller.user;

import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.exception.DareuWebApplicationException;
import com.dareu.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
                                        RedirectAttributes atts, Model model, @ModelAttribute("registrationResponse")EntityRegistrationResponse response)
                        throws DareuWebApplicationException{
        return memberService.discoverUsersView(pageNumber, atts, model, response); 
    }
    
    @RequestMapping("dares")
    public ModelAndView discoverDaresView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.discoverDaresView(pageNumber, atts); 
    }
    
    @RequestMapping("responses")
    public ModelAndView discoverResponsesView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.discoverResponsesView(pageNumber, atts); 
    }
    
    @RequestMapping(value = "users/request/{userId}", method = RequestMethod.POST)
    public String requestFriendship(@PathVariable(name = "userId", required = true)String userId, RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.requestFriendship(userId, atts); 
    }
    
    @RequestMapping(value = "users/request/{userId}")
    public String friendshipRequestResponse(@RequestParam("accepted")Boolean accepted, @PathVariable(name = "userId", required = true)String userId, RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.processFriendshipRequest(userId, accepted, atts); 
    }
    
    @RequestMapping(value = "users/profile")
    public ModelAndView userProfile(@RequestParam("userId")String userId)throws DareuWebApplicationException{
        return memberService.userProfile(userId);
    }
    
    @RequestMapping("trending")
    public ModelAndView discoverTrendingView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.discoverTrendingView(pageNumber, atts); 
    }
    
    @RequestMapping("sponsors")
    public ModelAndView discoverSponsorView(@RequestParam(required = false, defaultValue = "1", name = "pageNumber")int pageNumber, 
                                        RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.discoverSponsorsView(pageNumber, atts); 
    }
}
