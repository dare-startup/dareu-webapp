package com.dareu.web.exception;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public class DareuWebApplicationException extends RuntimeException{
    
    private ApplicationError applicationError; 

    public DareuWebApplicationException(ApplicationError applicationError) {
        this.applicationError = applicationError; 
    }

    public DareuWebApplicationException(String message) {
        super(message);
    }

    public DareuWebApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationError getApplicationError() {
        return applicationError;
    }
    
}
