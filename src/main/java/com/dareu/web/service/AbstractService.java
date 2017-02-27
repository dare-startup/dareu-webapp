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
    public static final String CHANGE_EMAIL_ADDRESS_MODEL = "changeEmailAddressRequest"; 
    public static final String MESSAGE_REQUEST_PARAMETER = "requestMessage"; 
    public static final String CATEGORIES_REQUEST_ATTRIBUTE = "categories"; 
    public static final String FRIENDS_REQUEST_ATTRIBUTE = "friends"; 
    public static final String USERS_REQUEST_ATTRIBUTE = "users"; 
    public static final String FRIENDSHIHP_REGISTRATION_REQUEST_ATTRIBUTE = "friendshipRegistration";
    public static final String PAGINATION_DATA_REQUEST_ATTRIBUTE = "paginationData"; 
    public static final String DARES_REQUEST_ATTRIBUTE = "dares"; 
    public static final String DARE_REQUEST_ATTRIBUTE = "dare"; 
    public static final String REGISTRATION_RESPONSE_REQUEST_ATTRIBUTE = "registrationResponse"; 
    public static final String ERROR_REQUEST_ATTRIBUTE = "error"; 
    public static final String UNACCEPTED_DARE_REQUEST_ATTRIBUTE = "unacceptedDare"; 
    public static final String DARE_ID_REQUEST_PARAMETER = "dareId";
    public static final String ACTIVE_DARE_REQUEST_ATTRIBUTE = "activeDare"; 
    public static final String CATEGORY_REQUEST_ATTRIBUTE = "category"; 
    
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
        REDIRECT_UPLOAD_RESPONSE("redirect:/member/dare/response/upload"), 
        REDIRECT_PROFILE("redirect:/member/profile"); 
        
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
        CONTACT("contact"),
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
