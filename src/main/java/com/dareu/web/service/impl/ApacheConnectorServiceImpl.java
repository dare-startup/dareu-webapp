package com.dareu.web.service.impl;

import com.dareu.web.conn.DareOperation;
import com.dareu.web.service.ApacheConnectorService;

import javax.ejb.Stateless;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author jose.rubalcaba
 */
@Stateless
public class ApacheConnectorServiceImpl implements ApacheConnectorService{
    
    public ApacheConnectorServiceImpl(){
        
    }

    @Override
    public <T> T requestApiOperation(DareOperation operation, Class<?> type) {
        CloseableHttpClient client = HttpClients.createDefault(); 
        return null; 
    }
}
