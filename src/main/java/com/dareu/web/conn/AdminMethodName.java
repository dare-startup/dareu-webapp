package com.dareu.web.conn;

/**
 *
 * @author jose.rubalcaba
 */
public enum AdminMethodName {
    USER_BY_EMAIL("/admin/account/findUserByEmail/%s"), 
    USERS_BY_PAGE("/admin/account/findUsers?pageNumber=%d"), 
    CATEGORIES("/dare/category"),
    NEW_CATEGORY("/admin/dare/category/create"), 
    UNAPPROVED_DARES("/admin/dare/unapproved?pageNumber=%d"), 
    DISCOVER_USERS("/account/discoverUsers?pageNumber=%d");
    
        
        private final String contextPath; 
        
        AdminMethodName(String contextPath){
            this.contextPath = contextPath; 
        }
        
        @Override
        public String toString(){
            return contextPath; 
        }
}
