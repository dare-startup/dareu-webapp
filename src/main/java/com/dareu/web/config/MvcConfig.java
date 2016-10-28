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

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean
                = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource());

        factoryBean.setPackagesToScan(new String[]{"com.dareu.data.entity"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        //vendorAdapter.setGenerateDdl(generateDdl)

        factoryBean.setJpaVendorAdapter(vendorAdapter);

        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.hbm2ddl.auto", "update");

        factoryBean.setJpaProperties(additionalProperties);

        return factoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DataSource src = null; 
        try {
            Context ctx = new InitialContext();
            src = (DataSource) ctx.lookup("java:jboss/mysql/datasources/dareu");
        } catch (NamingException ex) {
            src = null; 
        }
        return src; 
    }
    
     @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
