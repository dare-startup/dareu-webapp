package com.dareu.web.conn;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

/**
 *
 * @author jose.rubalcaba
 */
public class ApacheConnectionResponseHandler implements ResponseHandler<String>{

    @Override
    public String handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
