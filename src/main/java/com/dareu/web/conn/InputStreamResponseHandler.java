package com.dareu.web.conn;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public class InputStreamResponseHandler implements ResponseHandler<byte[]>{

    private final String path; 
    
    public InputStreamResponseHandler(String contextPath){
        this.path = contextPath; 
    }
    
    public byte[] handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
        //check status code 
        int statusCode = hr.getStatusLine().getStatusCode(); 
        if(statusCode == 200){
            HttpEntity entity = hr.getEntity();
            return IOUtils.toByteArray(entity.getContent()); 
        }else throw new IOException("Status code is not OK(200)\nReceived StatusCode " + statusCode); 
    }
    
}
