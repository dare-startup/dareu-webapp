package com.dareu.web.conn;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jose.rubalcaba
 */
public class ApacheResponseWrapper {
    private int statusCode; 
    private String jsonResponse; 
    private String contextPath; 
    private String date; 

    public ApacheResponseWrapper() {
        date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()); 
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getDate() {
        return date;
    }
}
