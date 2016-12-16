package com.dareu.web.exception;

/**
 *
 * @author jose.rubalcaba
 */
public class ConnectorManagerException extends Exception{

    public ConnectorManagerException(String message) {
        super(message);
    }

    public ConnectorManagerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
