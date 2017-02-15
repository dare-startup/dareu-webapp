package com.dareu.web.conn;

/**
 *
 * @author jose.rubalcaba
 */
public enum ProtectedMethodName {
    REQUEST_FRIENDSHIP("/account/friendship/%s/create"),
    FIND_FRIENDS("/account/friends/find?pageNumber=%d"), 
    FIND_FRIENDS_BY_QUERY("/account/friends/find?q=%s&pageNumber=%d"), 
    UPDATE_FRIENDSHIP("/account/friendship/%s/update?accepted=%s"), 
    FIND_DARE_DESCRIPTION("/dare/find?dareId="),
    CREATE_DARE("/dare/create"), 
    UNACCEPTED_DARE("/dare/unaccepted"), 
    DARE_CONFIRMATION("/dare/confirm"), 
    DISCOVER_DARES("/dare/discover?pageNumber=%d"),
    ACTIVE_DARE("/dare/active"), 
    GET_IMAGE_PROFILE("/account/profile/image"), 
    UPLOAD_DARE_RESPONSE("/dare/response/create"),
    ME("/account/me"), 
    CHANGE_EMAIL_ADDRESS("/account/changeEmailAddress");
    String contextPath; 
    ProtectedMethodName(String value){
        this.contextPath = value; 
    }
    
   @Override
   public String toString(){
       return contextPath; 
   }
}
