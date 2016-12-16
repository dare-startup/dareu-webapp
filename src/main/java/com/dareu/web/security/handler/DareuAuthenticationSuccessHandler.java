package com.dareu.web.security.handler;

import com.dareu.web.dto.security.SecurityRole;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.security.UserRole;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
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
        //get authorities 
        Collection<? extends GrantedAuthority> authorities = a.getAuthorities(); 
        //get role 
        GrantedAuthority auth = (GrantedAuthority)authorities.toArray()[0];
        //get string role 
        String role = auth.getAuthority(); 
        //parse to a security role 
        SecurityRole securityRole = SecurityRole.fromString(role); 
        //create path variable 
        String path = ""; 
        //creates a session 
        request.getSession(true).setAttribute("userToken", ((DareuUserDetails)a.getPrincipal()).getToken());
        //check paths
        switch(securityRole){
            case USER: 
                path = "/member/"; 
                break; 
            case ADMIN: 
                path = "/admin/"; 
                break; 
            case SPONSOR: 
                path = "/sponsor/"; 
                break; 
        }
        log.info(String.format("Redirecting to %s after successful signin", path)); 
        redirectStrategy.sendRedirect(request, response, path);
    }
    
}
