package com.dareu.web.service.impl;

import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DiscoverUserAccount;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Component

public class MemberServiceImpl implements MemberService {

    @Autowired
    private ConnectorManager connector; 
    
    @Autowired
    private HttpSession session; 
    
    public MemberServiceImpl() {
    }

    
    @Override
    public ModelAndView createDareView(Model model, RedirectAttributes atts) {
        model.addAttribute("dare", new CreateDareRequest());
        ModelAndView mav = new ModelAndView("user/create-dare");
        
        //get categories 
        Page<CategoryDescription> categories = null; 
        try{
            categories = connector.getCategories(1); 
            mav.addObject("categories", categories); 
            
        }catch(ConnectorManagerException ex){
            atts.addFlashAttribute("error", new ApplicationError("Could not get categories, try again", "/member/dare/create"));
            mav.setViewName("redirect:/error/appError");
            
        }
        return mav; 
    }

    @Override
    public ModelAndView discoverUsersView(int pageNumber, RedirectAttributes atts) {
        //get auth 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        DareuUserDetails details = (DareuUserDetails)auth.getPrincipal(); 
        ModelAndView mav = new ModelAndView("user/discover-users");
        try{
            Page<DiscoverUserAccount> page = connector.getDiscoverUsers(pageNumber, details.getToken()); 
            mav.addObject("users", page); 
        }catch(ConnectorManagerException ex){
            atts.addFlashAttribute("error", new ApplicationError("Could not get users, try again", "/member/"));
            mav.setViewName("redirect:/error/appError");
        }
        return mav; 
    }

    @Override
    public ModelAndView discoverDaresView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("user/discover-dares"); 
        return mav; 
    }

    @Override
    public ModelAndView discoverResponsesView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("user/discover-responses"); 
        return mav; 
    }

    public ModelAndView discoverTrendingView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("user/trending"); 
        return mav; 
    }

    public ModelAndView discoverSponsorsView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView("user/discover-sponsors"); 
        return mav; 
    }
    
}
