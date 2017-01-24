package com.dareu.web.security.handler;

import com.dareu.web.dto.security.SecurityRole;
import com.dareu.web.security.DareuUserDetails;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DareuAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private static final Logger log = Logger.getLogger(DareuAuthenticationSuccessHandler.class.getName());
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); 
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) 
            throws IOException, ServletException { 
        //creates a session 
        request.getSession(true).setAttribute("userToken", ((DareuUserDetails)a.getPrincipal()).getToken());
        
        
        response.sendRedirect(request.getContextPath());
    }
    
}
