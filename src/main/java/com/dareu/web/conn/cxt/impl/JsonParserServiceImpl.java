package com.dareu.web.conn.cxt.impl;

import com.dareu.web.conn.cxt.JsonParserService;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class JsonParserServiceImpl implements JsonParserService {
    
    
    public JsonParserServiceImpl(){
        
    }

    public <T> T parseJson(String json, Class<T> type) {
        return new Gson().fromJson(json, type); 
    }

    public String serialize(Object object) {
        return new Gson().toJson(object); 
    }

    public <T> T parseJson(String json, Type type) {
        return new Gson().fromJson(json, type); 
    }
}
