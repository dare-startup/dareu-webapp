package com.dareu.web.service.impl;

import com.dareu.web.service.ApacheConnectorService;

import javax.ejb.Stateless;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 *
 * @author jose.rubalcaba
 */
@Stateless
public class ApacheConnectorServiceImpl implements ApacheConnectorService{
    private CloseableHttpClient client; 
    
    public ApacheConnectorServiceImpl(){
        
    }
}
