package com.dareu.web.config;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author jose.rubalcaba
 */
public class DareuInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext(); 
        cxt.register(MvcConfig.class);
        cxt.setServletContext(sc);
        
        ServletRegistration.Dynamic servlet = sc.addServlet("dispatcher", new DispatcherServlet(cxt)); 
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/"); 
        
        FilterRegistration.Dynamic securityFilter = sc.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class); 
        securityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
    
    @Produces
    public Logger logbean(InjectionPoint ip){
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName()); 
    }
}
