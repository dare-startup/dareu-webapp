package com.dareu.web.conn;

/**
 *
 * @author jose.rubalcaba
 */
public enum DareOperation {
    SIGNIN("/rest/security/authenticate");
    
    String operationPath; 
    
    DareOperation(String path){
        this.operationPath = path; 
    }
    
    @Override 
    public String toString(){
        return this.operationPath; 
    }
}
