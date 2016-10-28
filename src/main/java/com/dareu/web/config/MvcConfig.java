package com.dareu.web.config;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
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
@Configuration
@EnableWebMvc()
@ComponentScan(basePackages = {"com.dareu.web", "com.dareu.web.service", "com.dareu.data.repository", 
                        "com.dareu.web.controller"})
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

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
