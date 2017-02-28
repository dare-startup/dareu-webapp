package com.dareu.web.conn;

/**
 *
 * @author jose.rubalcaba
 */
public enum PublicMethodName {
    SIGNUP("/open/registerUser"), 
    CONTACT("/open/contact"); 
    
    String contextPath;
    PublicMethodName(String value){
        this.contextPath = value;
    }
    
    @Override
    public String toString(){
        return this.contextPath;
    }
}
