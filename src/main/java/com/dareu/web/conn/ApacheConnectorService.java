package com.dareu.web.conn;

import com.dareu.web.conn.ApacheResponseWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
     * @param token
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performProtectedGetOperation(String methodName, String token) throws IOException;
    
    /**
     * protected post operation
     * @param methodName
     * @param postEntity
     * @param token
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performProtectedPostOperation(String methodName, Object postEntity, String token) throws IOException;
    
    /**
     * Load an image usign an input stream
     * @param methodName
     * @param token
     * @return
     * @throws IOException 
     */
    public byte[] loadImageProfile(String methodName, String token)throws IOException; 
    
    /**
     * Performs a multipart/form-data request to a protected resource
     * @param path
     * @param filePath
     * @param params
     * @param token
     * @return
     * @throws IOException 
     */
    public ApacheResponseWrapper performProtectedMultipartPostOperation(String path, String filePath, Map<String, String> params, String token)throws IOException; 
    
}
