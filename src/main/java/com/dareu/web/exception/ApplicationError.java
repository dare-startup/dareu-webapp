package com.dareu.web.exception;

/**
 *
 * @author jose.rubalcaba
 */
public class ApplicationError {
    private String errorMessage; 
    private String redirect; 

    public ApplicationError(String errorMessage, String redirect) {
        this.errorMessage = errorMessage;
        this.redirect = redirect;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
    
    
}
