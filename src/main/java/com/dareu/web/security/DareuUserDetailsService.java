package com.dareu.web.security;

import com.dareu.web.conn.AdminMethodName;
import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.dto.security.PasswordEncryptor;
import com.dareu.web.dto.security.SecurityRole;
import com.dareu.web.conn.ApacheConnectorService;
import com.dareu.web.conn.cxt.JsonParserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class DareuUserDetailsService implements UserDetailsService {

    @Autowired
    private ApacheConnectorService service;

    @Autowired
    private JsonParserService jsonParser;

    @Value("${com.dareu.web.admin.username}")
    private String adminEmail;

    @Value("${com.dareu.web.admin.password}")
    private String adminPassword;

    @Autowired
    private PasswordEncryptor encryptor; 
    
    private static final Logger log = Logger.getLogger(DareuUserDetailsService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        DareuUserDetails details = null;
        if (adminEmail.equals(string)) {
            details = new DareuUserDetails();
            details.setUsername(string);
            details.setPassword(encryptor.encryptPassword(adminPassword));
            details.setAuthorities(getAdminAuthorities());
            details.setIsAccountNonExpired(true);
            details.setIsAccountNonLocked(true);
            details.setIsEnabled(true);
            details.setIsCredentialsNonExpired(true);
            return details;
        } else {
            //load user by email 
            //SigninResponse user = null;
            ApacheResponseWrapper wrapper = null;
            try {
                wrapper = service.performSuperAdminGetOperation(
                        String.format(AdminMethodName.USER_BY_EMAIL.toString(), string));
                if (wrapper.getStatusCode() == 200) {
                    //parse json 
                    UserAccount account = jsonParser.parseJson(wrapper.getJsonResponse(), UserAccount.class);

                    //create a user details
                    details = new DareuUserDetails();
                    details.setUsername(string);
                    details.setPassword(account.getPassword());
                    details.setAuthorities(getUserAuthorities(account));
                    details.setIsAccountNonExpired(true);
                    details.setIsAccountNonLocked(true);
                    details.setIsEnabled(true);
                    details.setIsCredentialsNonExpired(true);
                    details.setToken(account.getToken()); 
                    return details;
                } else if(wrapper.getStatusCode() == 500){
                    log.severe(String.format("Could not fetch user:\n%s", wrapper.getJsonResponse()));
                    throw new UsernameNotFoundException("There has been an error, try again");
                }else if(wrapper.getStatusCode() == 404){
                    log.severe("Username and/or password are incorrect"); 
                    throw new UsernameNotFoundException("Username and/or password are incorrect"); 
                }
            } catch (IOException ex) {
                throw new UsernameNotFoundException("Username and/or password are incorrect");
            }
        }
        return null; 
    }

    private List<GrantedAuthority> getAdminAuthorities() {
        List<GrantedAuthority> auths = new ArrayList();
        auths.add(new UserRole(SecurityRole.ADMIN.toString()));
        return auths;
    }

    private List<GrantedAuthority> getUserAuthorities(UserAccount account) {
        List<GrantedAuthority> auths = new ArrayList();
        auths.add(new UserRole(account.getRole()));
        return auths;
    }

    /**
     * private List<GrantedAuthority> getAuthorities(DareUser user) {
     * List<GrantedAuthority> auths = new ArrayList(); //add role auths.add(new
     * UserRole(user.getRole().toString())); return auths; }*
     */
}
