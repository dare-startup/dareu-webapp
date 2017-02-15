package com.dareu.web.service.impl;

import com.dareu.web.dto.request.ChangeEmailAddressRequest;
import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.request.DareConfirmationRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.dto.response.UpdatedEntityResponse;
import com.dareu.web.dto.response.entity.AccountProfile;
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
        model.addAttribute(DARES_REQUEST_ATTRIBUTE, new CreateDareRequest());
        ModelAndView mav = new ModelAndView(getView(JspView.CREATE_DARE));

        //get auth
        DareuUserDetails details = getDetails();

        //get categories 
        Page<CategoryDescription> categories = null;
        Page<FriendSearchDescription> friends = null;
        try {
            categories = connector.getCategories(1);
            mav.addObject(CATEGORIES_REQUEST_ATTRIBUTE, categories);
            //get available friends 
            friends = connector.findAvailableFriends(1, details.getToken());
            mav.addObject(FRIENDS_REQUEST_ATTRIBUTE, friends);
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
            mav.addObject(USERS_REQUEST_ATTRIBUTE, page);
            if (registration != null && registration.getId() != null) {
                mav.addObject(FRIENDSHIHP_REGISTRATION_REQUEST_ATTRIBUTE, registration);
            }

            //get pagination data 
            PaginationData paginationData = paginationService.getPaginationData(page);
            mav.addObject(PAGINATION_DATA_REQUEST_ATTRIBUTE, paginationData);
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
            mav.addObject(DARES_REQUEST_ATTRIBUTE, dares);
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
            atts.addFlashAttribute(REGISTRATION_RESPONSE_REQUEST_ATTRIBUTE, response);

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
            atts.addFlashAttribute(REGISTRATION_RESPONSE_REQUEST_ATTRIBUTE, response);
            //redirect to same member/discover/users
            return getRedirect(Redirect.REDIRECT_DISCOVER_USERS);
        } catch (ConnectorManagerException ex) {
            atts.addFlashAttribute(ERROR_REQUEST_ATTRIBUTE,
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
                mav.addObject(UNACCEPTED_DARE_REQUEST_ATTRIBUTE, unacceptedDare);
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
                    atts.addFlashAttribute(DARE_ID_REQUEST_PARAMETER, confirmationRequest.getDareId());
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
            mav.addObject(ACTIVE_DARE_REQUEST_ATTRIBUTE, activeDare);

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
            mav.addObject(DARES_REQUEST_ATTRIBUTE, description);
            mav.addObject(DARE_ID_REQUEST_PARAMETER, dareId);
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
            flashMap.put(ERROR_REQUEST_ATTRIBUTE, new ApplicationError(exception.getApplicationError().getCode(),
                    getFriendlyMessage(exception.getApplicationError().getCode()),
                    exception.getApplicationError().getRedirect()));
        }
        return "redirect:/error/appError";
    }

    @Override
    public ModelAndView userProfile(String userId) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView("user/user-profile"); 
        return mav; 
    }

    @Override
    public ModelAndView currentUserProfile(String message) throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView("user/profile"); 
        try{
            if(message != null && ! message.isEmpty())
                mav.addObject("", message);
            String token = getDetails().getToken();
            AccountProfile profile = connector.currentUserProfile(token); 
            mav.addObject("profile", profile); 
            mav.addObject(CHANGE_EMAIL_ADDRESS_MODEL, new ChangeEmailAddressRequest()); 
            return mav; 
        }catch(ConnectorManagerException ex){
            throw new DareuWebApplicationException(
                    new ApplicationError(ApplicationError.ErrorCode.NETWORK_CONNECTION, 
                            ex.getMessage(), contextPath + "/"));
        }catch(Exception ex){
            throw new DareuWebApplicationException(
                    new ApplicationError(ApplicationError.ErrorCode.IO_ERROR, 
                            ex.getMessage(), contextPath + "/"));
        }
    }

    @Override
    public ModelAndView settingsView() throws DareuWebApplicationException {
        ModelAndView mav = new ModelAndView("user/settings"); 
        return mav; 
    }

    @Override
    public String changeEmailAddress(ChangeEmailAddressRequest request, RedirectAttributes atts) throws DareuWebApplicationException {
        try{
            String token = getDetails().getToken(); 
            UpdatedEntityResponse response = connector.updateEmailAddress(request, token); 
            if(response.isSuccess()){
                //sends a message to same page 
                atts.addFlashAttribute(MESSAGE_REQUEST_PARAMETER, "Your email has been updated"); 
            }
            return getRedirect(Redirect.REDIRECT_PROFILE); 
        }catch(ConnectorManagerException ex){
            throw new DareuWebApplicationException(
                    new ApplicationError(ApplicationError.ErrorCode.NETWORK_CONNECTION, 
                            ex.getMessage(), contextPath + "/member/profile")); 
        }
    }

}
