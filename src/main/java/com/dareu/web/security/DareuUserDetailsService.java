package com.dareu.web.security;

import com.dareu.data.entity.DareUser;
import com.dareu.data.exception.DataAccessException;
import com.dareu.data.repository.DareUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DareuUserDetailsService implements UserDetailsService{

    @Inject
    private DareUserRepository repository; 
    
    @Inject
    private Logger log; 
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        if(repository == null){
            log.severe("Repository is null");
            return null; 
        }
        
        //load user by email 
        DareUser user = null;
        try{
            user = repository.findUserByEmail(string); 
            if(user == null)
                return null; 
            //create a user details
            DareuUserDetails details = new DareuUserDetails(); 
            details.setUsername(string);
            details.setPassword(user.getPassword());
            details.setAuthorities(getAuthorities(user));
            details.setIsAccountNonExpired(true);
            details.setIsAccountNonLocked(true);
            details.setIsEnabled(true);
            details.setIsCredentialsNonExpired(true);
            return details; 
        }catch(DataAccessException ex){
            log.severe("Could not find user by email: " + ex.getMessage());
            return null; 
        }
    }

    private List<GrantedAuthority> getAuthorities(DareUser user) {
        List<GrantedAuthority> auths = new ArrayList(); 
        //add role 
        auths.add(new UserRole(user.getRole().toString())); 
        return auths; 
    }
    
}
