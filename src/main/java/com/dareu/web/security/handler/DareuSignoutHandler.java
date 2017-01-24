package com.dareu.web.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@Component
public class DareuSignoutHandler implements LogoutHandler{

    public void logout(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) {
        //deletes 
    }
    
}
