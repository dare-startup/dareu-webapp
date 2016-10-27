package com.dareu.web.service.impl;

import com.dareu.web.conn.ApacheConnectionResponseHandler;
import com.dareu.web.conn.DareOperation;
import com.dareu.web.service.ApacheConnectorService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class ApacheConnectorServiceImpl implements ApacheConnectorService{
    
    private static final String HOST = "http://localhost:8080"; 
    
    private static final Logger log = Logger.getLogger("ApacheConnectorService"); 
    
    public ApacheConnectorServiceImpl(){
        
    }

    @Override
    public <T> T requestApiOperation(DareOperation operation, Class<?> type, MethodType method, 
            Object postEntity, boolean auth, String authHeader) {
        CloseableHttpClient client = new DefaultHttpClient(); 
        HttpResponse response = null; 
        if(method == MethodType.GET){
            //create a get 
            HttpGet get = new HttpGet(HOST + operation.toString());
            get.addHeader("Accept", "application/json");
            if(auth){
                get.addHeader("Authorization", authHeader);
            }
            try{
                response = client.execute(get); 
            }catch(IOException ex){
                
            }
        }else if(method == MethodType.POST){
            HttpPost post = new HttpPost(HOST + operation.toString()); 
            post.addHeader("Content-Type", "application/json");
            
            StringEntity entity = null; 
            try{
                entity = new StringEntity(new Gson().toJson(postEntity));
                post.setEntity(entity);
                response = client.execute(post); 
            }catch(UnsupportedEncodingException ex){
                
            }catch(IOException ex){
                log.severe("Could not connect to service: " + ex.getMessage());
            }
        }
        return null; 
    }
}
