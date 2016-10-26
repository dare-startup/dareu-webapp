package com.dareu.web.security;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author jose.rubalcaba
 */
public class UserRole implements GrantedAuthority {

    String userRole; 
    
    public UserRole(String name){
        this.userRole = name; 
    }
    
    public void setAuthority(String auth){
        this.userRole = auth; 
    }
    
    @Override
    public String getAuthority() {
        return this.userRole; 
    }
    
}
