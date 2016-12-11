package com.dareu.web.service;

import java.lang.reflect.Type;

/**
 *
 * @author jose.rubalcaba
 */
public interface JsonParserService {
    
    /**
     * Parse a json object using reflection
     * @param <T>
     * @param json
     * @param type
     * @return 
     */
    public <T> T parseJson(String json, Type type); 
    
    /**
     * parse a json using a class instance
     * @param <T>
     * @param json
     * @param type
     * @return 
     */
    public <T> T parseJson(String json, Class<T> type); 
    
    /**
     * Serialized an object to json
     * @param object
     * @return 
     */
    public String serialize(Object object); 
}
