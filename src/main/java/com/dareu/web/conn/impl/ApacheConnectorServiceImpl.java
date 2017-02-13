package com.dareu.web.conn.impl;

import com.dareu.web.conn.ApacheConnectionResponseHandler;
import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.conn.ApacheConnectorService;
import com.dareu.web.conn.InputStreamResponseHandler;
import com.dareu.web.conn.cxt.JsonParserService;
import com.dareu.web.mgr.FileManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
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
    
    @Autowired
    private FileManager fileManager; 

    private HttpClient client;

    public ApacheConnectorServiceImpl() {
        client = new DefaultHttpClient();
    }

    @Override
    public ApacheResponseWrapper performSuperAdminGetOperation(String contextPath) throws IOException {
        return createAdminGetRequest(contextPath);
    }

    public ApacheResponseWrapper performSuperAdminPostOperation(String contextPath, Object postEntity, boolean multipart) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApacheResponseWrapper performPublicGetOperation(String contextPath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApacheResponseWrapper performPublicPostOperation(String contextPath, Object postEntity) throws IOException {
        return createPublicPostRequest(contextPath, postEntity);
    }

    @Override
    public ApacheResponseWrapper performProtectedGetOperation(String methodName, String token) throws IOException {
        String url = host + methodName;
        log.info(String.format("Creating GET request to %s", url));
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", token);
        get.setHeader("Accept", "Application/json");
        //execute
        return client.execute(get, new ApacheConnectionResponseHandler(methodName));
    }

    @Override
    public ApacheResponseWrapper performProtectedPostOperation(String contextPath, Object postEntity, String token) throws IOException {
        String url = host + contextPath;
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("Authorization", token);
        String json = jsonParser.serialize(postEntity);
        HttpEntity entity = null;
        try {
            if (postEntity != null) {
                entity = new StringEntity(json);
                post.setEntity(entity);
            }
            log.info(String.format("Creating POST request to: %s\nEntity\n%s", contextPath, json));
            return client.execute(post, new ApacheConnectionResponseHandler(contextPath));
        } catch (UnsupportedEncodingException ex) {
            log.severe(String.format("UnsupportedEncondingException: %s", ex.getMessage()));
            return null;
        } catch (IOException ex) {
            log.severe(String.format("IOException: %s", ex.getMessage()));
            return null;
        }
    }

    private ApacheResponseWrapper createAdminGetRequest(String contextPath) throws IOException {
        String url = host + contextPath;
        log.info(String.format("Creating GET request to %s", url));
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", administratorToken);
        get.setHeader("Accept", "Application/json");
        //execute
        return client.execute(get, new ApacheConnectionResponseHandler(contextPath));
    }

    @Override
    public ApacheResponseWrapper performSuperAdminPostOperation(String contextPath, Object postEntity) throws IOException {
        String url = host + contextPath;
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("Authorization", administratorToken);
        String json = jsonParser.serialize(postEntity);
        HttpEntity entity = null;
        try {
            entity = new StringEntity(json);
            post.setEntity(entity);
            log.info(String.format("Creating POST request to: %s\nEntity\n%s", contextPath, json));
            return client.execute(post, new ApacheConnectionResponseHandler(contextPath));
        } catch (UnsupportedEncodingException ex) {
            log.severe(String.format("UnsupportedEncondingException: %s", ex.getMessage()));
            return null;
        } catch (IOException ex) {
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
        try {
            entity = new StringEntity(json);
            post.setEntity(entity);
            log.info(String.format("Creating POST request to: %s\nEntity\n%s", contextPath, json));
            return client.execute(post, new ApacheConnectionResponseHandler(contextPath));
        } catch (UnsupportedEncodingException ex) {
            log.severe(String.format("UnsupportedEncondingException: %s", ex.getMessage()));
            return null;
        } catch (IOException ex) {
            log.severe(String.format("IOException: %s", ex.getMessage()));
            return null;
        }
    }

    @Override
    public byte[] loadImageProfile(String methodName, String token) throws IOException {
        String url = host + methodName;
        HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "image/jpeg");
        get.setHeader("Authorization", token);
        return client.execute(get, new InputStreamResponseHandler(methodName));
    }
    
    @Override
    public ApacheResponseWrapper performProtectedMultipartPostOperation(String path, String filePath, Map<String, String> params, String token) throws IOException {
        String url = host + path; 
        HttpPost post = new HttpPost(url); 
        post.addHeader("Authorization", token);
        post.addHeader("Accept", "application/json"); 
        
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE); 
        ContentBody fileBody = new InputStreamBody(fileManager.getInputStream(filePath), "file"); 
        entity.addPart("file", fileBody);
        
        //add all string parts 
        ContentBody stringBody; 
        for(String key : params.keySet()){
            stringBody = new StringBody(params.get(key), Charset.forName("UTF-8")); 
            entity.addPart(key, stringBody);
        }
        
        post.setEntity(entity);
        return client.execute(post, new ApacheConnectionResponseHandler(path)); 
    }

}
