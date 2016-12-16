package com.dareu.web.conn.impl;

import com.dareu.web.conn.ApacheConnectionResponseHandler;
import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.conn.ApacheConnectorService;
import com.dareu.web.conn.cxt.JsonParserService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class ApacheConnectorServiceImpl implements ApacheConnectorService {

    private static final Logger log = Logger.getLogger("ApacheConnectorService");
    
    @Value("${com.dareu.web.admin.token}")
    private String administratorToken; 
    
    @Value("${com.web.server.port}")
    private String host; 
    
    @Autowired
    private JsonParserService jsonParser; 
    
    private HttpClient client; 

    public ApacheConnectorServiceImpl() {
        client = new DefaultHttpClient(); 
    }

    public ApacheResponseWrapper performSuperAdminGetOperation(String contextPath) throws IOException {
        return createAdminGetRequest(contextPath); 
    }

    public ApacheResponseWrapper performSuperAdminPostOperation(String contextPath, Object postEntity, boolean multipart) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ApacheResponseWrapper performPublicGetOperation(String contextPath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ApacheResponseWrapper performPublicPostOperation(String contextPath, Object postEntity) throws IOException {
        return createPublicPostRequest(contextPath, postEntity); 
    }

    public ApacheResponseWrapper performProtectedGetOperation(String methodName, String token) throws IOException {
        String url = host + methodName; 
        log.info(String.format("Creating GET request to %s", url));
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", token);
        get.setHeader("Accept", "Application/json");
        //execute
        return client.execute(get, new ApacheConnectionResponseHandler(methodName)); 
    }

    public ApacheResponseWrapper performProtectedPostOperation(String methodName, Object postEntity, String token) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ApacheResponseWrapper createAdminGetRequest(String contextPath) throws IOException{
        String url = host + contextPath; 
        log.info(String.format("Creating GET request to %s", url));
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", administratorToken);
        get.setHeader("Accept", "Application/json");
        //execute
        return client.execute(get, new ApacheConnectionResponseHandler(contextPath)); 
    }

    public ApacheResponseWrapper performSuperAdminPostOperation(String contextPath, Object postEntity) throws IOException {
        String url = host + contextPath;
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("Authorization", administratorToken);
        String json = jsonParser.serialize(postEntity);
        HttpEntity entity = null; 
        try{
            entity = new StringEntity(json);
            post.setEntity(entity);
            log.info(String.format("Creating POST request to: %s\nEntity\n%s", contextPath, json));
            return client.execute(post, new ApacheConnectionResponseHandler(contextPath));
        }catch(UnsupportedEncodingException ex){
            log.severe(String.format("UnsupportedEncondingException: %s", ex.getMessage()));
            return null; 
        }catch(IOException ex){
            log.severe(String.format("IOException: %s", ex.getMessage()));
            return null; 
        }
    }

    private ApacheResponseWrapper createPublicPostRequest(String contextPath, Object postEntity) {
        String url = host + contextPath;
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        String json = jsonParser.serialize(postEntity);
        HttpEntity entity = null; 
        try{
            entity = new StringEntity(json);
            post.setEntity(entity);
            log.info(String.format("Creating POST request to: %s\nEntity\n%s", contextPath, json));
            return client.execute(post, new ApacheConnectionResponseHandler(contextPath));
        }catch(UnsupportedEncodingException ex){
            log.severe(String.format("UnsupportedEncondingException: %s", ex.getMessage()));
            return null; 
        }catch(IOException ex){
            log.severe(String.format("IOException: %s", ex.getMessage()));
            return null; 
        }
    }

}
