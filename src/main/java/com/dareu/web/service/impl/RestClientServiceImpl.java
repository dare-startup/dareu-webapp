package com.dareu.web.service.impl;

import com.dareu.web.dto.client.AccountClientService;
import com.dareu.web.dto.response.entity.FriendSearchDescription;
import com.dareu.web.dto.response.entity.Page;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.RestClientService;
import java.io.IOException;
import java.util.logging.Logger;
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
    private AccountClientService accountClientService;
    
    public Page<FriendSearchDescription> findFriends(int pageNumber, String query) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DareuUserDetails details = (DareuUserDetails) auth.getPrincipal();

        try {
            return accountClientService.findFriends(query, pageNumber, details.getToken())
                    .execute()
                    .body();
        } catch (IOException ex) {
            return new Page();
        }
    }

}
