package com.dareu.web.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author jose.rubalcaba
 */
public class DareuAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest hsr, HttpServletResponse hsr1, AccessDeniedException ade) throws IOException, ServletException {
        
    }
    
}
