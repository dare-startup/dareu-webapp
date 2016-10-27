package com.dareu.web.config;

import com.dareu.data.security.SecurityRole;
import com.dareu.web.security.DareuAuthenticationProvider;
import com.dareu.web.security.DareuUserDetailsService;
import com.dareu.web.security.handler.DareuAccessDeniedHandler;
import com.dareu.web.security.handler.DareuAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author jose.rubalcaba
 */
@EnableWebSecurity(debug = true)
@ComponentScan(basePackages = { "com.dareu.web", "com.dareu.web.service", 
    "com.dareu.data.repository", "com.dareu.web.security"})
public class SecurityConfig {

    @Autowired
    private DareuAuthenticationProvider provider;
    
    @Autowired
    private DareuUserDetailsService service; 

    @Configuration
    @Order(1)
    public class UserHttpSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/user/**").hasRole(SecurityRole.USER.toString())
                    .and()
                    .csrf()
                    .and()
                    .authenticationProvider(provider)
                    .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/security/authenticate")
                    .passwordParameter("password")
                    .usernameParameter("email")
                    .failureUrl("/signin?retry")
                    .successHandler(new DareuAuthenticationSuccessHandler())
                    .and()
                    .userDetailsService(new DareuUserDetailsService())
                    .exceptionHandling()
                    .accessDeniedHandler(new DareuAccessDeniedHandler());
        }
    }

    @Configuration
    @Order(2)
    public class AdminHttpSecurityConfig extends WebSecurityConfigurerAdapter {
        
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole(SecurityRole.ADMIN.toString())
                    .and()
                    .csrf()
                    .and()
                    .authenticationProvider(provider)
                    .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/security/authenticate")
                    .passwordParameter("password")
                    .usernameParameter("email")
                    .failureUrl("/signin?retry")
                    .successHandler(new DareuAuthenticationSuccessHandler())
                    .and()
                    .userDetailsService(new DareuUserDetailsService())
                    .exceptionHandling()
                    .accessDeniedHandler(new DareuAccessDeniedHandler());
        }
    }

    @Configuration
    @Order(3)
    public class SponsorHttpSecurityConfig extends WebSecurityConfigurerAdapter {
        
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/sponsor/**").hasRole(SecurityRole.SPONSOR.toString())
                    .and()
                    .csrf()
                    .and()
                    .authenticationProvider(provider)
                    .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/security/authenticate")
                    .passwordParameter("password")
                    .usernameParameter("email")
                    .failureUrl("/signin?retry")
                    .successHandler(new DareuAuthenticationSuccessHandler())
                    .and()
                    .userDetailsService(new DareuUserDetailsService())
                    .exceptionHandling()
                    .accessDeniedHandler(new DareuAccessDeniedHandler());
        }
    }
}
