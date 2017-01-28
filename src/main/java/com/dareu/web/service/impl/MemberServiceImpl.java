package com.dareu.web.service.impl;

import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.request.DareConfirmationRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.UpdatedEntityResponse;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.DiscoverUserAccount;
import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.PaginationData;
import com.dareu.web.dto.response.entity.UnacceptedDare;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AbstractService;
import com.dareu.web.service.MemberService;
import com.dareu.web.service.PaginationService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class MemberServiceImpl extends AbstractService implements MemberService {

    @Autowired
    private ConnectorManager connector;

    @Value("#{servletContext.contextPath}")
    protected String contextPath;
    
    @Autowired
    private PaginationService paginationService; 

    private static final Logger log = Logger.getLogger(MemberService.class.getName());

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
        try {
            categories = connector.getCategories(1);
            mav.addObject("categories", categories);
            //get available friends 
            friends = connector.findAvailableFriends(1, details.getToken());
            mav.addObject("friends", friends);
        } catch (ConnectorManagerException ex) {
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
        try {
            Page<DiscoverUserAccount> page = connector.getDiscoverUsers(pageNumber, details.getToken());
            mav.addObject("users", page);
            if (registration != null && registration.getId() != null) {
                mav.addObject("friendshipRegistration", registration);
            }
            
            //get pagination data 
            PaginationData paginationData = paginationService.getPaginationData(page); 
            mav.addObject("paginationData", paginationData); 
        } catch (ConnectorManagerException ex) {
            atts.addFlashAttribute("error", new ApplicationError("Could not get users, try again", contextPath + "/member/"));
            mav.setViewName(getRedirect(Redirect.ERROR_REDIRECT));
        }
        return mav;
    }

    @Override
    public ModelAndView discoverDaresView(int pageNumber, RedirectAttributes atts) {
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_DARES));
        String token = getDetails().getToken();
        try{
            Page<DareDescription> dares = connector.discoverDares(pageNumber, token);
            mav.addObject("dares", dares); 
            return mav; 
        }catch(ConnectorManagerException ex){
            atts.addFlashAttribute("error", new ApplicationError("Could not get discoverable dares, please try again", 
                    contextPath));
            mav.setViewName(getRedirect(Redirect.ERROR_REDIRECT));
            return mav; 
        }
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
        try {
            DareuUserDetails details = getDetails();
            String token = details.getToken();
            String currentUserId = details.getId();

            EntityRegistrationResponse response = connector.requestFriendship(userId, token);
            atts.addFlashAttribute("registrationResponse", response);

            return getRedirect(Redirect.REDIRECT_DISCOVER_USERS);
        } catch (ConnectorManagerException ex) {
            atts.addFlashAttribute("error",
                    new ApplicationError("Could not send friendship request",
                            contextPath + "/member/discover/users"));
            return getRedirect(Redirect.ERROR_REDIRECT);
        }
    }

    public String processFriendshipRequest(String userId, Boolean accepted, RedirectAttributes atts) {
        String token = getDetails().getToken();
        try {
            EntityRegistrationResponse response = connector.updateFriendshipRequest(userId, accepted, token);
            atts.addFlashAttribute("registrationResponse", response);
            //redirect to same member/discover/users
            return getRedirect(Redirect.REDIRECT_DISCOVER_USERS);
        } catch (ConnectorManagerException ex) {
            atts.addFlashAttribute("error",
                    new ApplicationError("Could not process request",
                            contextPath + "/member/discover/users"));
            return getRedirect(Redirect.ERROR_REDIRECT);
        }
    }

    public String createDare(CreateDareRequest request, RedirectAttributes atts) {
        String token = getDetails().getToken();
        try {
            EntityRegistrationResponse response = connector.createDare(request, token);
            atts.addFlashAttribute(MemberService.REGISTRATION_RESPONSE, response);
            atts.addFlashAttribute(MemberService.SUCCESS_TYPE, token);
            return getRedirect(Redirect.REDIRECT_MEMBER_SUCESS);
        } catch (ConnectorManagerException ex) {
            atts.addFlashAttribute("error", new ApplicationError("Could not create dare",
                    contextPath + "/member/"));
            return getRedirect(Redirect.ERROR_REDIRECT);
        }
    }

    public ModelAndView defaultView() {
        String token = getDetails().getToken();
        ModelAndView mav = new ModelAndView("user/index");
        try {
            UnacceptedDare unacceptedDare = connector.getUnacceptedDare(token);
            if (unacceptedDare != null) {
                mav.addObject("unacceptedDare", unacceptedDare);
            }

        } catch (ConnectorManagerException ex) {
            log.severe("Error on defaultView: " + ex.getMessage());
        }
        return mav;
    }

    public ModelAndView hotView() {
        return new ModelAndView("user/hot");
    }

    public ModelAndView successView(String successType, EntityRegistrationResponse response) {
        ModelAndView mav = new ModelAndView("success");
        mav.addObject(MemberService.SUCCESS_TYPE, successType);
        mav.addObject(MemberService.REGISTRATION_RESPONSE, response);

        return mav;
    }

    public ModelAndView anchoredView() {
        return new ModelAndView("user/anchored");
    }

    public String confirmDareRequest(DareConfirmationRequest confirmationRequest, RedirectAttributes atts) {
        String token = getDetails().getToken();
        String confirmationValue = confirmationRequest.isAccepted() ? "accept" : "decline";
        try {
            UpdatedEntityResponse response = connector.confirmDareRequest(confirmationRequest, token);
            if (response != null) {
                //check if the response is a success one 
                if (response.isSuccess()) {
                    //success
                    return getRedirect(Redirect.REDIRECT_INDEX);
                } else {
                    atts.addFlashAttribute("error", new ApplicationError(String.format("Could not %s dare", confirmationValue),
                            contextPath + "/member/"));
                    return getRedirect(Redirect.ERROR_REDIRECT);
                }
            } else {
                atts.addFlashAttribute("error", new ApplicationError(String.format("Could not %s dare", confirmationValue),
                        contextPath + "/member/"));
                return getRedirect(Redirect.ERROR_REDIRECT);
            }
        } catch (ConnectorManagerException ex) {
            log.severe(ex.getMessage());
            atts.addFlashAttribute("error", new ApplicationError(String.format("Could not %s dare", confirmationValue),
                    contextPath + "/member/"));
            return getRedirect(Redirect.ERROR_REDIRECT);
        }
    }

}
