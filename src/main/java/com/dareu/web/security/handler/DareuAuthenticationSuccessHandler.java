package com.dareu.web.security.handler;

import com.dareu.data.security.SecurityRole;
import com.dareu.web.security.UserRole;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author jose.rubalcaba
 */
public class DareuAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

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
        //check paths
        switch(securityRole){
            case USER: 
                path = "/user/"; 
                break; 
            case ADMIN: 
                path = "/admin/"; 
                break; 
            case SPONSOR: 
                path = "/sponsor/"; 
                break; 
        }
        
        response.sendRedirect(path);
        //TODO: AFTER SUCCESS AUTHENTICATION
    }
    
}
