package com.dareu.web.service;

import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public interface RestClientService {
    
    /**
     * find friend description by page number
     * @param pageNumber
     * @param query
     * @return 
     */
    public Page<FriendSearchDescription> findFriends(int pageNumber, String query);
    
}
