package com.dareu.web.config;

import com.dareu.data.security.SecurityRole;
import com.dareu.web.security.DareuAuthenticationManager;
import com.dareu.web.security.DareuUserDetailsService;
import com.dareu.web.security.handler.DareuAccessDeniedHandler;
import com.dareu.web.security.handler.DareuAuthenticationSuccessHandler;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author jose.rubalcaba
 */
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class UserHttpSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/user/**").hasRole(SecurityRole.USER.toString())
                .and()
                    .csrf()
                .and()
                .authenticationProvider((AuthenticationProvider)new DareuAuthenticationManager())
                .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/security/authenticate")
                    .passwordParameter("password")
                    .usernameParameter("email")
                    .failureUrl("signin?retry")
                    .successHandler(new DareuAuthenticationSuccessHandler())
                .and()
                    .userDetailsService(new DareuUserDetailsService())
                    .exceptionHandling()
                    .accessDeniedHandler(new DareuAccessDeniedHandler())
                    ;
        }
    }

    @Configuration
    @Order(2)
    public static class AdminHttpSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole(SecurityRole.ADMIN.toString())
                .and()
                    .csrf()
                .and()
                .authenticationProvider((AuthenticationProvider)new DareuAuthenticationManager())
                .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/security/authenticate")
                    .passwordParameter("password")
                    .usernameParameter("email")
                    .failureUrl("signin?retry")
                    .successHandler(new DareuAuthenticationSuccessHandler())
                .and()
                    .userDetailsService(new DareuUserDetailsService())
                    .exceptionHandling()
                    .accessDeniedHandler(new DareuAccessDeniedHandler());
        }
    }

    @Configuration
    @Order(3)
    public static class SponsorHttpSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/sponsor/**").hasRole(SecurityRole.SPONSOR.toString())
                .and()
                    .csrf()
                .and()
                .authenticationProvider((AuthenticationProvider)new DareuAuthenticationManager())
                .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/security/authenticate")
                    .passwordParameter("password")
                    .usernameParameter("email")
                    .failureUrl("signin?retry")
                    .successHandler(new DareuAuthenticationSuccessHandler())
                .and()
                    .userDetailsService(new DareuUserDetailsService())
                    .exceptionHandling()
                    .accessDeniedHandler(new DareuAccessDeniedHandler());
        }
    }
}
