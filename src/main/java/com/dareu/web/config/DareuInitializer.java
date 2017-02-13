package com.dareu.web.config;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author jose.rubalcaba
 */
public class DareuInitializer implements WebApplicationInitializer{

    private final String tmp = System.getProperty("dareu.multipart.tmp.directory");
    private static final Logger log = Logger.getLogger(DareuInitializer.class.getName()); 
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        //application context
        AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext(); 
        cxt.register(MvcConfig.class);
        cxt.setServletContext(sc);
        
        ServletRegistration.Dynamic servlet = sc.addServlet("dispatcher", new DispatcherServlet(cxt)); 
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/"); 
        log.info("Setting multipart temporal folder to " + tmp); 
        servlet.setMultipartConfig(new MultipartConfigElement(tmp, 52428800L, 52428800L, 0));
        
        //security filter
        FilterRegistration.Dynamic securityFilter = sc.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class); 
        securityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
    
    @Produces
    public Logger logbean(InjectionPoint ip){
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName()); 
    }
}
