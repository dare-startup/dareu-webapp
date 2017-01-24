package com.dareu.web.security;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public final class ApplicationUser {
    private final String id; 
    private final String token; 

    public ApplicationUser(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
    
    
}
