package com.dareu.web.service;

import com.dareu.web.exception.ApplicationError;
import com.dareu.web.security.DareuUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public abstract class AbstractService {
    
    //constants
    public static final String SUCCESS_TYPE = "successType";
    public static final String REGISTRATION_RESPONSE = "registrationResponse"; 
    
    protected String getView(JspView view){
        return view.toString(); 
    }
    
    public static enum SuccessType{
        ENTITY_CREATED("registration"), 
        ENTITY_UPDATED("update"), 
        ENTITY_DELETED("delete"); 
        String value; 
        SuccessType(String value){
            this.value = value; 
        }
        
        public SuccessType fromString(String s){
            if(s.equals(ENTITY_CREATED.value)){
                return ENTITY_CREATED; 
            }else if(s.equals(ENTITY_UPDATED.value)){
                return ENTITY_UPDATED; 
            }else if(s.equals(ENTITY_DELETED.value)){
                return ENTITY_DELETED; 
            }
            return null; 
        }
        
        @Override
        public String toString(){
            return this.value; 
        }
    }
    
    
    protected String getRedirect(Redirect redirect){
        return redirect.toString(); 
    }
    
    protected DareuUserDetails getDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        DareuUserDetails details = (DareuUserDetails)auth.getPrincipal(); 
        return details; 
    }
    
    protected String getFriendlyMessage(ApplicationError.ErrorCode code){
        switch (code) {
            case NETWORK_CONNECTION:
                return "There is something wrong with our network, please try again later, error code: " + code.toString();
            default:
                throw new AssertionError();
        }
    }
    
    
    protected enum Redirect{
        ERROR_REDIRECT("redirect:/error/appError"), 
        REDIRECT_DISCOVER_USERS("redirect:/member/discover/users"), 
        REDIRECT_MEMBER_SUCESS("redirect:/member/success"), 
        REDIRECT_INDEX("redirect:/"), 
        REDIRECT_UPLOAD_RESPONSE("redirect:/member/dare/response/upload"); 
        
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
