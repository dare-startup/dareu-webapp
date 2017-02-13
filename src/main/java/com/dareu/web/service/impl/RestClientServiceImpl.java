package com.dareu.web.service.impl;

import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.exception.ConnectorManagerException;
import com.dareu.web.mgr.ConnectorManager;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.RestClientService;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@Component
public class RestClientServiceImpl implements RestClientService {

    private static final Logger log = Logger.getLogger(RestClientServiceImpl.class.getName());

    @Autowired
    private ConnectorManager connector;
    

    public Page<FriendSearchDescription> findFriends(int pageNumber, String query) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DareuUserDetails details = (DareuUserDetails) auth.getPrincipal();

        try {
            return connector.findAvailableFriends(pageNumber, query, details.getToken());
        } catch (ConnectorManagerException ex) {
            return new Page();
        }
    }

    public byte[] getProfileImage(String userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DareuUserDetails details = (DareuUserDetails) auth.getPrincipal();

        try {
            byte[] bytes  = connector.getProfileImage(userId, details.getToken());
            if(bytes.length == 0){
                //get account image 
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("/resources/img/account.png"); 
                bytes = IOUtils.toByteArray(stream); 
            }
            return bytes;
        } catch (ConnectorManagerException ex) {
            return new byte[0];
        } catch (Exception ex) {
            log.severe("Could not load image profile: " + ex.getMessage());
            return new byte[0];
        }
    }

}
