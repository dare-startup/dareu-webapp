package com.dareu.web.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author jose.rubalcaba
 */
public class DareuUserDetails implements UserDetails{

    private List<GrantedAuthority> authorities; 
    private String password; 
    private String username; 
    private boolean isAccountNonExpired; 
    private boolean isAccountNonLocked; 
    private boolean isCredentialsNonExpired; 
    private boolean isEnabled; 
    private String token; 
    
    public DareuUserDetails(){
        
    } 

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public boolean isIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public boolean isIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    

    @Override
    public String getPassword() {
        return this.password; 
    }

    @Override
    public String getUsername() {
        return this.username; 
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked; 
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired; 
    }

    @Override
    public boolean isEnabled() {
        return isEnabled; 
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
    
}
