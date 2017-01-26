package com.dareu.web.conn;

import java.io.IOException;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jose.rubalcaba
 */
public class ApacheConnectionResponseHandler implements ResponseHandler<ApacheResponseWrapper>{
    
    private static final Logger log = Logger.getLogger(ApacheConnectionResponseHandler.class.getName()); 
    private final String contextPath; 
    
    public ApacheConnectionResponseHandler(String contextPath){
        this.contextPath = contextPath; 
    }

    @Override
    public ApacheResponseWrapper handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
        ApacheResponseWrapper wrapper = new ApacheResponseWrapper(); 
        //get status code 
        int statusCode = hr.getStatusLine().getStatusCode(); 
        
        HttpEntity entity = hr.getEntity();
        if(entity != null){
            //get json response 
            String json = EntityUtils.toString(hr.getEntity(), "UTF-8"); 
            wrapper.setJsonResponse(json);
        }else wrapper.setJsonResponse("");
        
        wrapper.setStatusCode(statusCode);
        wrapper.setContextPath(contextPath);
        if(statusCode != 200)
            //debugging purposes
            log.info(String.format("StatusCode: %d\nContextPath: %s\nResponse: %s", 
                    statusCode, contextPath, wrapper.getJsonResponse()));
        
        return wrapper; 
    }
    
}
