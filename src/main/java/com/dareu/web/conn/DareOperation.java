package com.dareu.web.conn;

/**
 *
 * @author jose.rubalcaba
 */
public enum DareOperation {
    SIGNIN("/dareu/rest/security/authenticate"), 
    CATEGORIES("/dareu/rest/dare/category"), 
    FIND_USER_BY_EMAIL("/dareu/rest/admin/account/findUserByEmail/%s");
    
    String operationPath; 
    
    DareOperation(String path){
        this.operationPath = path; 
    }
    
    @Override 
    public String toString(){
        return this.operationPath; 
    }
}
