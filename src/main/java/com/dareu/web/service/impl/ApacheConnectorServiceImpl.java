package com.dareu.web.service.impl;

import com.dareu.web.conn.ApacheConnectionResponseHandler;
import com.dareu.web.conn.DareOperation;
import com.dareu.web.service.ApacheConnectorService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class ApacheConnectorServiceImpl implements ApacheConnectorService {

    private static final String HOST = "http://localhost:8080";
    private static final String SERVER_ID = "25f1071687c8b1a1db535eef30eefb48"; //HARDCODED SERVER ID

    private static final Logger log = Logger.getLogger("ApacheConnectorService");

    public ApacheConnectorServiceImpl() {

    }

    @Override
    public <T> T performSuperAdminGetOperation(SuperAdminMethodType methodType, String operation, Type type) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            HttpGet get = new HttpGet(HOST + operation);
            get.addHeader("ServerId", SERVER_ID);
            get.addHeader("Accept", "application/json");
            response = client.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                String json = EntityUtils.toString(response.getEntity());
                return new Gson().fromJson(json, type);
            } else {
                log.info("Got unauthorized response from dareu services on " + get.getURI().toString());
                return null; 
            }

        } catch (IOException ex) {
            log.severe("Could not perform HTTP GET: " + ex.getMessage());
            return null;
        }
    }
}
