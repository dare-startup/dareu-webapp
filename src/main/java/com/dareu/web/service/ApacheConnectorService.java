package com.dareu.web.service;

import com.dareu.web.conn.DareOperation;
import com.dareu.web.service.ApacheConnectorService.SuperAdminMethodType;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 *
 * @author jose.rubalcaba
 */
public interface ApacheConnectorService {
    
    public <T> T performSuperAdminGetOperation(SuperAdminMethodType methodType, String operation, Type type)throws IOException;
    
    
    
    public static enum SuperAdminMethodType{
        USER_BY_EMAIL
    }        
            
    public static enum MethodType{
        POST, GET;
    }
}
