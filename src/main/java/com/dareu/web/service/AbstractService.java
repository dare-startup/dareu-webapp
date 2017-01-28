package com.dareu.web.service;

import com.dareu.web.security.DareuUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public abstract class AbstractService {
    
    protected String getView(JspView view){
        return view.toString(); 
    }
    
    
    
    protected String getRedirect(Redirect redirect){
        return redirect.toString(); 
    }
    
    protected DareuUserDetails getDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        DareuUserDetails details = (DareuUserDetails)auth.getPrincipal(); 
        return details; 
    }
    
    
    protected enum Redirect{
        ERROR_REDIRECT("redirect:/error/appError"), 
        REDIRECT_DISCOVER_USERS("redirect:/member/discover/users"), 
        REDIRECT_MEMBER_SUCESS("redirect:/member/success"), 
        REDIRECT_INDEX("redirect:/"); 
        
        String path; 
        Redirect(String value){
            this.path = value; 
        }
        
        @Override 
        public String toString(){
            return path; 
        }
    }
    
    protected enum JspView{
        DEFAULT_INDEX("index"),
        SIGNIN("signin"), 
        SIGNUP("signup"), 
        CREATE_DARE("user/create-dare"), 
        DISCOVER_USERS("user/discover-users"),
        DISCOVER_DARES("user/discover-dares"), 
        DISCOVER_RESPONSES("user/discover-responses"), 
        TRENDING("user/trending"), 
        DISCOVER_SPONSORS("user/discover-sponsors");
        String viewName; 
        JspView(String value){
            this.viewName = value; 
        }
        @Override 
        public String toString(){
            return viewName; 
        }
    }
}
