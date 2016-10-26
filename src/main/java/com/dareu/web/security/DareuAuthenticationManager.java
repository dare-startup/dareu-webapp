package com.dareu.web.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author jose.rubalcaba
 */
public class DareuAuthenticationManager implements AuthenticationManager{

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getPrincipal().toString(); 
        String password = a.getCredentials().toString(); 
        
        //TODO: encrypt password and username and connect to rest api
        
        return null; 
    }
    
}
