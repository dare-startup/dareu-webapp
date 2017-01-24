package com.dareu.web.security.handler;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DareuAccessDeniedHandler implements AccessDeniedHandler{

    private static final Logger log = Logger.getLogger(DareuAccessDeniedHandler.class.getName());
    
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException ex) throws IOException, ServletException {
        log.info("AccessDenied: " + ex.getMessage());
        log.info(String.format("LocalizedMessage: %s", ex.getLocalizedMessage()));
        res.sendRedirect(req.getContextPath() + "/error/unauthorized");
    }
    
}
