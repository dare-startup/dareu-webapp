package com.dareu.web.client;

import com.dareu.web.dto.client.AccountClientService;
import com.dareu.web.dto.client.DareClientService;
import com.dareu.web.dto.client.OpenClientService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@Component
public class RetroFactory {
    
    @Value("#{com.web.server.port}")
    private String baseUrl;
    
    private static Retrofit retrofitInstance;
    
    private Retrofit getInstance(){
        if(retrofitInstance == null){
            OkHttpClient client = new OkHttpClient();
            
            retrofitInstance = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(client)
                    .build();
        }
        return retrofitInstance;
    }
    
    @Bean
    public AccountClientService accountClientServiceBean(){
        return getInstance().create(AccountClientService.class);
    }
    
    @Bean
    public DareClientService dareClientServiceBean(){
        return getInstance().create(DareClientService.class);
    }
    
    @Bean
    public OpenClientService openClientServiceBean(){
        return getInstance().create(OpenClientService.class);
    }
}
