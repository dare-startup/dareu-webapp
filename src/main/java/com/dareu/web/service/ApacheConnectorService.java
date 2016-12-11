package com.dareu.web.service;

import com.dareu.web.conn.ApacheResponseWrapper;
import java.io.IOException;

/**
 *
 * @author jose.rubalcaba
 */
public interface ApacheConnectorService {
    
    /**
     * admin get operation
     * @param contextPath
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performSuperAdminGetOperation(String contextPath) throws IOException ;
    
    /**
     * admin post operation
     * @param contextPath
     * @param postEntity
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performSuperAdminPostOperation(String contextPath, Object postEntity) throws IOException ;
    
    /**
     * public get operation 
     * @param contextPath
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performPublicGetOperation(String contextPath) throws IOException;
    
    /**
     * public post operation
     * @param methodName
     * @param postEntity
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performPublicPostOperation(String methodName, Object postEntity) throws IOException;
    
    /**
     * protected get operation
     * @param methodName
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performProtectedGetOperation(String methodName) throws IOException;
    
    /**
     * protected post operation
     * @param methodName
     * @param postEntity
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performProtectedPostOperation(String methodName, Object postEntity) throws IOException;
    
}
