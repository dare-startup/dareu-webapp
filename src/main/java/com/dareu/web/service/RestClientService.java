package com.dareu.web.service;

import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import java.io.InputStream;

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

    /**
     * 
     * @param userId
     * @return 
     */
    public byte[] getProfileImage(String userId);
    
}
