package com.dareu.web.service.impl;

import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.request.DareConfirmationRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.UpdatedEntityResponse;
import com.dareu.web.dto.response.entity.ActiveDare;
import com.dareu.web.dto.response.entity.CategoryDescription;
import com.dareu.web.dto.response.entity.DareDescription;
import com.dareu.web.dto.response.entity.DiscoverUserAccount;
import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.dto.response.entity.PaginationData;
import com.dareu.web.dto.response.entity.UnacceptedDare;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.exception.DareuWebApplicationException;
import com.dareu.web.mgr.ConnectorManager;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AbstractService;
import com.dareu.web.service.MemberService;
import com.dareu.web.service.PaginationService;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

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
    public ModelAndView createDareView(Model model, RedirectAttributes atts) throws DareuWebApplicationException {
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
            throw new DareuWebApplicationException(new ApplicationError("Could not get categories, try again",
                    contextPath + "/member/dare/create"));
        }
        return mav;
    }

    @Override
    public ModelAndView discoverUsersView(int pageNumber, RedirectAttributes atts,
            Model model, EntityRegistrationResponse registration) throws DareuWebApplicationException {
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
            throw new DareuWebApplicationException(new ApplicationError("Could not get users, try again", contextPath + "/member/"));
        }
        return mav;
    }

    @Override
    public ModelAndView discoverDaresView(int pageNumber, RedirectAttributes atts) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_DARES));
        String token = getDetails().getToken();
        try {
            Page<DareDescription> dares = connector.discoverDares(pageNumber, token);
            mav.addObject("dares", dares);
            return mav;
        } catch (ConnectorManagerException ex) {
            throw new DareuWebApplicationException(new ApplicationError("Could not get discoverable dares, please try again",
                    contextPath));
        }
    }

    @Override
    public ModelAndView discoverResponsesView(int pageNumber, RedirectAttributes atts) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_RESPONSES));
        return mav;
    }

    @Override
    public ModelAndView discoverTrendingView(int pageNumber, RedirectAttributes atts) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView(getView(JspView.TRENDING));
        return mav;
    }

    @Override
    public ModelAndView discoverSponsorsView(int pageNumber, RedirectAttributes atts) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView(getView(JspView.DISCOVER_SPONSORS));
        return mav;
    }

    @Override
    public String requestFriendship(String userId, RedirectAttributes atts) throws DareuWebApplicationException {
        try {
            DareuUserDetails details = getDetails();
            String token = details.getToken();
            String currentUserId = details.getId();

            EntityRegistrationResponse response = connector.requestFriendship(userId, token);
            atts.addFlashAttribute("registrationResponse", response);

            return getRedirect(Redirect.REDIRECT_DISCOVER_USERS);
        } catch (ConnectorManagerException ex) {
            throw new DareuWebApplicationException(new ApplicationError("Could not send friendship request",
                    contextPath + "/member/discover/users"));
        }
    }

    @Override
    public String processFriendshipRequest(String userId, Boolean accepted, RedirectAttributes atts) throws DareuWebApplicationException{
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

    @Override
    public String createDare(CreateDareRequest request, RedirectAttributes atts) throws DareuWebApplicationException {
        String token = getDetails().getToken();
        try {
            EntityRegistrationResponse response = connector.createDare(request, token);
            atts.addFlashAttribute(REGISTRATION_RESPONSE, response);
            atts.addFlashAttribute(SUCCESS_TYPE, SuccessType.ENTITY_CREATED.toString());
            return getRedirect(Redirect.REDIRECT_MEMBER_SUCESS);
        } catch (ConnectorManagerException ex) {
            throw new DareuWebApplicationException(new ApplicationError("Could not create dare",
                    contextPath + "/member/"));
        }
    }

    @Override
    public ModelAndView defaultView() throws DareuWebApplicationException {
        String token = getDetails().getToken();
        ModelAndView mav = new ModelAndView("user/index");
        try {
            UnacceptedDare unacceptedDare = connector.getUnacceptedDare(token);
            if (unacceptedDare != null) {
                mav.addObject("unacceptedDare", unacceptedDare);
            }

        } catch (ConnectorManagerException ex) {
            throw new DareuWebApplicationException(new ApplicationError(ApplicationError.ErrorCode.NETWORK_CONNECTION,
                    "Could not connect to server, please try again", contextPath));
        }
        return mav;
    }

    @Override
    public ModelAndView hotView() throws DareuWebApplicationException{
        return new ModelAndView("user/hot");
    }

    @Override
    public ModelAndView successView(String successType, EntityRegistrationResponse response) {
        ModelAndView mav = new ModelAndView("success");
        mav.addObject(SUCCESS_TYPE, successType);
        mav.addObject(REGISTRATION_RESPONSE, response);

        return mav;
    }

    @Override
    public ModelAndView anchoredView() {
        return new ModelAndView("user/anchored");
    }

    @Override
    public String confirmDareRequest(DareConfirmationRequest confirmationRequest, RedirectAttributes atts) throws DareuWebApplicationException {
        String token = getDetails().getToken();
        String confirmationValue = confirmationRequest.isAccepted() ? "accept" : "decline";
        try {
            UpdatedEntityResponse response = connector.confirmDareRequest(confirmationRequest, token);
            if (response != null) {
                //check if the response is a success one 
                if (response.isSuccess()) {
                    //success
                    atts.addFlashAttribute("dareId", confirmationRequest.getDareId());
                    return getRedirect(Redirect.REDIRECT_UPLOAD_RESPONSE);
                } else {
                    throw new DareuWebApplicationException(new ApplicationError(String.format("Could not %s dare", confirmationValue),
                            contextPath + "/member/"));
                }

            } else {
                throw new DareuWebApplicationException(new ApplicationError(String.format("Could not %s dare", confirmationValue),
                        contextPath + "/member/"));
            }

        } catch (ConnectorManagerException ex) {
            throw new DareuWebApplicationException(new ApplicationError(String.format("Could not %s dare", confirmationValue),
                    contextPath + "/member/"));
        }
    }

    @Override
    public ModelAndView currentActiveDare(RedirectAttributes atts) throws DareuWebApplicationException {
        String token = getDetails().getToken();
        ModelAndView mav;
        try {
            ActiveDare activeDare = connector.getCurrentActiveDare(token);
            mav = new ModelAndView("user/active");
            mav.addObject("activeDare", activeDare);

            return mav;
        } catch (ConnectorManagerException ex) {
            throw new DareuWebApplicationException(new ApplicationError("Could not get active dare, try again",
                    contextPath + "/member/"));
        }
    }

    @Override
    public String uploadDareResponse(MultipartFile file, String comment, String dareId, RedirectAttributes atts) throws DareuWebApplicationException {
        //validate file 
        if (file != null) {
            try {
                EntityRegistrationResponse response = connector.uploadDareResponse(file.getInputStream(), dareId, comment, getDetails().getToken());
                atts.addFlashAttribute(REGISTRATION_RESPONSE,  response);
                atts.addFlashAttribute(SUCCESS_TYPE, SuccessType.ENTITY_CREATED); 
                return getRedirect(Redirect.REDIRECT_MEMBER_SUCESS);
            } catch (ConnectorManagerException ex) {
                throw new DareuWebApplicationException(
                        new ApplicationError(ApplicationError.ErrorCode.NETWORK_CONNECTION, ex.getMessage(), contextPath + "/"));
            } catch (IOException ex) {
                throw new DareuWebApplicationException(
                        new ApplicationError(ApplicationError.ErrorCode.IO_ERROR, ex.getMessage(), contextPath + "/"));
            }
        } else {
            atts.addFlashAttribute("error", new ApplicationError("No file was attached to request", contextPath + "/"));
            return getRedirect(Redirect.ERROR_REDIRECT);
        }
    }

    @Override
    public ModelAndView uploadDareResponseView(String dareId) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView("user/upload-dare-response");
        //get dare 
        try {
            DareDescription description = connector.findDareDescription(dareId, getDetails().getToken());
            mav.addObject("dare", description);
            mav.addObject("dareId", dareId);
            return mav;
        } catch (ConnectorManagerException ex) {
            throw new DareuWebApplicationException(
                    new ApplicationError(ApplicationError.ErrorCode.NETWORK_CONNECTION,
                            "There has been an error connecting to server, try again", contextPath + "/"));
        }
    }

    @Override
    public String handleException(DareuWebApplicationException exception, HttpServletRequest req) {
        log.severe("Got DareuWebApplicationException[ExceptionHandler]: "
                + exception.getApplicationError().getErrorMessage());
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(req);
        if (flashMap != null) {
            flashMap.put("error", new ApplicationError(exception.getApplicationError().getCode(),
                    getFriendlyMessage(exception.getApplicationError().getCode()),
                    exception.getApplicationError().getRedirect()));
        }
        return "redirect:/error/appError";
    }

    public ModelAndView userProfile(String userId) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView("user/user-profile"); 
        return mav; 
    }

    public ModelAndView currentUserProfile() throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView("user/profile"); 
        try{
            connector.
        }catch(ConnectorManagerException ex){
            
        }catch(Exception ex){
            
        }
        return mav; 
    }

    public ModelAndView settingsView() throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView("user/settings"); 
        return mav; 
    }

}
