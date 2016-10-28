package com.dareu.web.security;

import com.dareu.data.entity.DareUser;
import com.dareu.data.exception.DataAccessException;
import com.dareu.data.repository.DareUserRepository;
import com.dareu.web.conn.DareOperation;
import com.dareu.web.service.ApacheConnectorService;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ApacheConnectorService service; 
    
    
    
    private static final Logger log = Logger.getLogger(DareuUserDetailsService.class.getName()); 
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        //load user by email 
        DareUser user = null;
        try{
            user =  service.performSuperAdminGetOperation(ApacheConnectorService.SuperAdminMethodType.USER_BY_EMAIL, 
                    String.format(DareOperation.FIND_USER_BY_EMAIL.toString(), string), 
                    new TypeToken<DareUser>(){}.getType()); 
            if(user == null)
                throw new UsernameNotFoundException("Username not found"); 
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
        }catch(IOException ex){
            log.severe("Could not get user: " + ex.getMessage());
            throw new UsernameNotFoundException("Username not found"); 
        }
    }

    private List<GrantedAuthority> getAuthorities(DareUser user) {
        List<GrantedAuthority> auths = new ArrayList(); 
        //add role 
        auths.add(new UserRole(user.getRole().toString())); 
        return auths; 
    }
    
}
