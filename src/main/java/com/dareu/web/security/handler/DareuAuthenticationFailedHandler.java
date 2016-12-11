package com.dareu.web.security.handler;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DareuAuthenticationFailedHandler implements AuthenticationFailureHandler{

    private static final Logger log = Logger.getLogger(DareuAuthenticationFailedHandler.class.getName());
    
    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
        log.info(String.format("Authentication failed: %s", ae.getMessage()));
        hsr1.sendRedirect("/signin?rety");
    }
    
}
