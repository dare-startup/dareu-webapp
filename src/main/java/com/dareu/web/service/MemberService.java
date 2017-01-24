package com.dareu.web.service;

import com.dareu.web.dto.response.EntityRegistrationResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
public interface MemberService {

    /**
     * creates a new dare for a member user
     * @param model
     * @param atts
     * @return 
     */
    public ModelAndView createDareView(Model model, RedirectAttributes atts);

    /**
     * get discover users view
     * @param pageNumber
     * @param atts
     * @param model
     * @return 
     */
    public ModelAndView discoverUsersView(int pageNumber, RedirectAttributes atts, 
            Model model, EntityRegistrationResponse response);

    /**
     * get discover dares view 
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverDaresView(int pageNumber, RedirectAttributes atts);

    /**
     * get discover responses view
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverResponsesView(int pageNumber, RedirectAttributes atts);

    /**
     * get a discover trending view
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverTrendingView(int pageNumber, RedirectAttributes atts);

    /**
     * get a discover sponsors view
     * @param pageNumber
     * @param atts
     * @return 
     */
    public ModelAndView discoverSponsorsView(int pageNumber, RedirectAttributes atts);

    /**
     * Requests a new friendship
     * @param userId
     * @param atts
     * @return 
     */
    public String requestFriendship(String userId, RedirectAttributes atts);

    /**
     * Process a friendship request to the accepted parameter
     * @param userId
     * @param accepted
     * @param atts
     * @return 
     */
    public String processFriendshipRequest(String userId, Boolean accepted, RedirectAttributes atts);
    
}
