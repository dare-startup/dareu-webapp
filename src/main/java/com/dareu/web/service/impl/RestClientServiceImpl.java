package com.dareu.web.service.impl;

import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@Component
public class RestClientServiceImpl implements RestClientService{

    @Autowired
    private ConnectorManager connector;
    
    public Page<FriendSearchDescription> findFriends(int pageNumber, String query) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        DareuUserDetails details = (DareuUserDetails)auth.getPrincipal(); 
        
        try{
            return connector.findAvailableFriends(pageNumber, query, details.getToken());
        }catch(ConnectorManagerException ex){
            return new Page(); 
        }
    }
    
}
