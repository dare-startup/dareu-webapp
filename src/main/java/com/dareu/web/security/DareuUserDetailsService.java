package com.dareu.web.security;

import com.dareu.web.client.SuperAdminClientService;
import com.dareu.web.dto.response.entity.UserAccount;
import com.dareu.web.dto.security.PasswordEncryptor;
import com.dareu.web.dto.security.SecurityRole;
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
import retrofit2.Response;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DareuUserDetailsService implements UserDetailsService {

    @Value("${com.dareu.web.admin.token}")
    private String administratorToken;

    @Value("${com.dareu.web.admin.username}")
    private String adminEmail;

    @Value("${com.dareu.web.admin.password}")
    private String adminPassword;

    @Autowired
    private PasswordEncryptor encryptor;

    @Autowired
    private SuperAdminClientService superAdminClientService;

    private static final Logger log = Logger.getLogger(DareuUserDetailsService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        DareuUserDetails details = new DareuUserDetails();
        if (adminEmail.equals(string)) {
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

            try {
                Response<UserAccount> response = superAdminClientService.findUserByEmail(string, administratorToken)
                        .execute();
                switch (response.code()) {
                    case 200:
                        details.setUsername(string);
                        details.setPassword(response.body().getPassword());
                        details.setAuthorities(getUserAuthorities(response.body()));
                        details.setIsAccountNonExpired(true);
                        details.setIsAccountNonLocked(true);
                        details.setIsEnabled(true);
                        details.setIsCredentialsNonExpired(true);
                        details.setToken(response.body().getToken());
                        details.setId(response.body().getId());
                        return details;
                    case 404:
                        log.severe("Username and/or password are incorrect"); 
                        throw new UsernameNotFoundException("Username and/or password are incorrect");
                    case 500:
                        log.severe(String.format("Could not fetch user:\n%s", response.errorBody().string())); 
                        throw new UsernameNotFoundException("There has been an error, try again");
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
