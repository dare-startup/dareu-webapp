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
    CREATE_DARE("/dare/create"), 
    UNACCEPTED_DARE("/dare/unaccepted");
    String contextPath; 
    ProtectedMethodName(String value){
        this.contextPath = value; 
    }
    
   @Override
   public String toString(){
       return contextPath; 
   }
}
