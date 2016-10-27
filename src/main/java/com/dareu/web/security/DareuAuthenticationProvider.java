package com.dareu.web.security;

import com.dareu.data.entity.DareUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DareuAuthenticationProvider implements AuthenticationProvider {

    
    @Autowired
    private DareuUserDetailsService service; 
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName(); 
        String pass = a.getCredentials().toString();
        
        UserDetails user = service.loadUserByUsername(username); 
        if(user == null)
            throw new BadCredentialsException("Username and/or password are incorrect"); 
        if(! pass.equals(user.getPassword()))
            throw new BadCredentialsException("Username and/or password are incorrect"); 
        
        return new UsernamePasswordAuthenticationToken(user, pass, user.getAuthorities()); 
    }

    @Override
    public boolean supports(Class<?> type) {
        return true; 
    }
    
}
