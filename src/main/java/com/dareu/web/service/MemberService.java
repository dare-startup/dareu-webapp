package com.dareu.web.service;

import com.dareu.web.dto.request.ChangeEmailAddressRequest;
import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.request.DareConfirmationRequest;
import com.dareu.web.dto.response.EntityRegistrationResponse;
import com.dareu.web.exception.DareuWebApplicationException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
public interface MemberService {
    
    /**
     * Returns a member success view
     * @param successType
     * @param response
     * @return 
     */
    public ModelAndView successView(String successType, EntityRegistrationResponse response);
    
    /**
     * Returns a member index view
     * @return 
     * @throws com.dareu.web.exception.DareuWebApplicationException 
     */
    public ModelAndView defaultView()throws DareuWebApplicationException;
    
    /**
     * Returns a member hot view
     * @return 
     * @throws com.dareu.web.exception.DareuWebApplicationException 
     */
    public ModelAndView hotView()throws DareuWebApplicationException;
    
    /**
     * Returns a member anchored view
     * @return 
     * @throws com.dareu.web.exception.DareuWebApplicationException 
     */
    public ModelAndView anchoredView()throws DareuWebApplicationException;
    
    /**
     * creates a new dare for a member user
     * @param model
     * @param atts
     * @return 
     */
    public ModelAndView createDareView(Model model, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * get discover users view
     * @param pageNumber
     * @param atts
     * @param model
     * @param response
     * @return 
     */
    public ModelAndView discoverUsersView(int pageNumber, RedirectAttributes atts, 
            Model model, EntityRegistrationResponse response)throws DareuWebApplicationException;

    /**
     * get discover dares view 
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverDaresView(int pageNumber, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * get discover responses view
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverResponsesView(int pageNumber, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * get a discover trending view
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverTrendingView(int pageNumber, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * get a discover sponsors view
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverSponsorsView(int pageNumber, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * Requests a new friendship
     * @param userId
     * @param atts
     * @return 
     */
    public String requestFriendship(String userId, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * Process a friendship request to the accepted parameter
     * @param userId
     * @param accepted
     * @param atts
     * @return 
     */
    public String processFriendshipRequest(String userId, Boolean accepted, RedirectAttributes atts)throws DareuWebApplicationException;
    
    /**
     * Creates a new dare 
     * @param request
     * @param atts
     * @return 
     */
    public String createDare(CreateDareRequest request, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * Confirms a dare request to an accepted value
     * @param confirmationRequest
     * @param atts
     * @return 
     */
    public String confirmDareRequest(DareConfirmationRequest confirmationRequest, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * Returns a currently active dare
     * @param atts
     * @return 
     */
    public ModelAndView currentActiveDare(RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * Uploads a new file response
     * @param file
     * @param comment
     * @param dareId
     * @param atts
     * @return 
     */
    public String uploadDareResponse(MultipartFile file, String comment, String dareId, RedirectAttributes atts)throws DareuWebApplicationException;

    /**
     * get the upload response view 
     * @param dareId
     * @return 
     */
    public ModelAndView uploadDareResponseView(String dareId)throws DareuWebApplicationException;
    
    /**
     * Handles an exception
     * @param ex
     * @param req
     * @return 
     */
    public String handleException(DareuWebApplicationException ex, HttpServletRequest req); 

    /**
     * Returns a user profile
     * @param userId
     * @return
     * @throws DareuWebApplicationException 
     */
    public ModelAndView userProfile(String userId)throws DareuWebApplicationException;

    /**
     * Get current logged in user profile page
     * @param message
     * @return
     * @throws DareuWebApplicationException 
     */
    public ModelAndView currentUserProfile(String message) throws DareuWebApplicationException;

    /**
     * returns a member settings page
     * @return
     * @throws DareuWebApplicationException 
     */
    public ModelAndView settingsView() throws DareuWebApplicationException;

    
    /**
     * Change an email address to a new one
     * @param request
     * @param atts
     * @return
     * @throws DareuWebApplicationException 
     */
    public String changeEmailAddress(ChangeEmailAddressRequest request, RedirectAttributes atts)throws DareuWebApplicationException;
    
    
}
