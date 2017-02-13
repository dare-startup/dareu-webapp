package com.dareu.web.exception;

/**
 *
 * @author jose.rubalcaba
 */
public class ApplicationError {
    private ErrorCode code;
    private String errorMessage; 
    private String redirect; 

    public ApplicationError(ErrorCode code, String errorMessage, String redirect) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.redirect = redirect;
    }

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

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }
    
    
    
    public static enum ErrorCode{
        NETWORK_CONNECTION("network_error"), 
        IO_ERROR("io_error"); 
        String value; 
        ErrorCode(String value){
            this.value = value; 
        }
    }
}
