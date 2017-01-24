package com.dareu.web.service.impl;

import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DiscoverUserAccount;
import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AbstractService;
import com.dareu.web.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class MemberServiceImpl  extends AbstractService implements MemberService {

    @Autowired
    private ConnectorManager connector; 
    
    @Value("#{servletContext.contextPath}")
    protected String contextPath; 
    
    public MemberServiceImpl() {
    }

    
    @Override
    public ModelAndView createDareView(Model model, RedirectAttributes atts) {
        model.addAttribute("dare", new CreateDareRequest());
        ModelAndView mav = new ModelAndView(getView(JspView.CREATE_DARE));
        
        //get auth
        DareuUserDetails details = getDetails();
        
        
        //get categories 
        Page<CategoryDescription> categories = null; 
        Page<FriendSearchDescription> friends = null; 
        try{
            categories = connector.getCategories(1); 
            mav.addObject("categories", categories); 
            //get available friends 
            friends = connector.findAvailableFriends(1, details.getToken()); 
            mav.addObject("friends", friends); 
        }catch(ConnectorManagerException ex){
            atts.addFlashAttribute("error", 
                    new ApplicationError("Could not get categories, try again", contextPath + "/member/dare/create"));
            mav.setViewName(getRedirect(Redirect.ERROR_REDIRECT));            
        }
        return mav; 
    }

    @Override
    public ModelAndView discoverUsersView(int pageNumber, RedirectAttributes atts, 
            Model model, EntityRegistrationResponse registration) {
        //get auth 
        DareuUserDetails details = getDetails();
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_USERS));
        try{
            Page<DiscoverUserAccount> page = connector.getDiscoverUsers(pageNumber, details.getToken()); 
            mav.addObject("users", page); 
            if(registration != null && registration.getId() != null)
                mav.addObject("friendshipRegistration", registration);
        }catch(ConnectorManagerException ex){
            atts.addFlashAttribute("error", new ApplicationError("Could not get users, try again", contextPath + "/member/"));
            mav.setViewName(getRedirect(Redirect.ERROR_REDIRECT));
        }
        return mav; 
    }

    @Override
    public ModelAndView discoverDaresView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_DARES)); 
        return mav; 
    }

    @Override
    public ModelAndView discoverResponsesView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_RESPONSES)); 
        return mav; 
    }

    public ModelAndView discoverTrendingView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView(getView(JspView.TRENDING)); 
        return mav; 
    }

    public ModelAndView discoverSponsorsView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_SPONSORS)); 
        return mav; 
    }

    public String requestFriendship(String userId, RedirectAttributes atts) {
        try{
            DareuUserDetails details = getDetails(); 
            String token = details.getToken();
            String currentUserId = details.getId();
            
            EntityRegistrationResponse response = connector.requestFriendship(userId, token); 
            atts.addFlashAttribute("registrationResponse", response); 
            
            return getRedirect(Redirect.REDIRECT_DISCOVER_USERS); 
        }catch(ConnectorManagerException ex){
            atts.addFlashAttribute("error", 
                    new ApplicationError("Could not send friendship request", 
                            contextPath + "/member/discover/users"));
            return getRedirect(Redirect.ERROR_REDIRECT);
        }
    }

    public String processFriendshipRequest(String userId, Boolean accepted, RedirectAttributes atts) {
        String token = getDetails().getToken(); 
        try{
            EntityRegistrationResponse response = connector.updateFriendshipRequest(userId, accepted, token); 
            atts.addFlashAttribute("registrationResponse", response); 
            //redirect to same member/discover/users
            return getRedirect(Redirect.REDIRECT_DISCOVER_USERS);
        }catch(ConnectorManagerException ex){
            atts.addFlashAttribute("error", 
                    new ApplicationError("Could not process request", 
                            contextPath + "/member/discover/users"));
            return getRedirect(Redirect.ERROR_REDIRECT);
        }
    }
    
}
