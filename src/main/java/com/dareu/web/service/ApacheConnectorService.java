package com.dareu.web.service;

import com.dareu.web.conn.DareOperation;

/**
 *
 * @author jose.rubalcaba
 */
public interface ApacheConnectorService {
    public <T> T requestApiOperation(DareOperation operation, Class<?> type, MethodType method, 
            Object postEntity, boolean auth, String authHeader);
    
    public static enum MethodType{
        POST, GET;
    }
}
