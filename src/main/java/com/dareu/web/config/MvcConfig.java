package com.dareu.web.config;

import com.dareu.web.dto.security.PasswordEncryptor;
import com.dareu.web.dto.security.impl.PasswordEncryptorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author jose.rubalcaba
 */
@EnableWebMvc()
@ComponentScan(basePackages = {"com.dareu.web", "com.dareu.web.service", "com.dareu.data.repository", 
                        "com.dareu.web.controller", "com.dareu.web.client" })
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
        vr.setViewClass(JstlView.class);
        vr.setOrder(0);
        return vr;
    }
    
    @Bean
    public ViewResolver userViewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/views/user/", ".jsp");
        vr.setOrder(1);
        vr.setViewClass(JstlView.class);
        return vr; 
   }
    
    @Bean
    public ViewResolver adminViewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/views/admin/", ".jsp");
        vr.setViewClass(JstlView.class);
        vr.setOrder(2);
        return vr;
    }
    
    @Bean
    public ViewResolver sponsorViewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/views/sponsor/", ".jsp");
        vr.setViewClass(JstlView.class);
        vr.setOrder(3);
        return vr;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    public PasswordEncryptor passwordEncryptor(){
        return new PasswordEncryptorImpl();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
