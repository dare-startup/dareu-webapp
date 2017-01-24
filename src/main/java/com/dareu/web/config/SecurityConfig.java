package com.dareu.web.config;

import com.dareu.web.dto.security.SecurityRole;
import com.dareu.web.security.DareuAuthenticationProvider;
import com.dareu.web.security.DareuUserDetailsService;
import com.dareu.web.security.handler.DareuAccessDeniedHandler;
import com.dareu.web.security.handler.DareuAuthenticationFailedHandler;
import com.dareu.web.security.handler.DareuAuthenticationSuccessHandler;
import com.dareu.web.security.handler.DareuSignoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.bind.support.AuthenticationPrincipalArgumentResolver;

/**
 *
 * @author jose.rubalcaba
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity(debug = false)
@ComponentScan(basePackages = { "com.dareu.web", "com.dareu.web.service", 
    "com.dareu.data.repository", "com.dareu.web.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DareuAuthenticationProvider provider;
    
    @Autowired
    private DareuUserDetailsService service; 
    
    @Autowired
    private DareuAuthenticationSuccessHandler successHandler;
    
    @Autowired
    private DareuAccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private DareuAuthenticationFailedHandler failureHandler;
    
    @Autowired
    private DareuSignoutHandler signoutHandler; 

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder builder)throws Exception{
        builder.userDetailsService(service);
        builder.authenticationProvider(provider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signin", "/signup", "/", "/about", "/contact").permitAll()
                
                .antMatchers("/error/**").authenticated()
                .antMatchers("/member/**").hasAnyAuthority("USER", "SPONSOR", "ADMIN")
                .antMatchers("/sponsor/**").hasAuthority("SPONSOR")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/signin").loginProcessingUrl("/security/authenticate")
                    .usernameParameter("email").passwordParameter("password")
                    .failureHandler(failureHandler).defaultSuccessUrl("/", true)
                    .successHandler(successHandler)
                .and()
                .logout().logoutUrl("/security/signout").deleteCookies("JSESSIONID").clearAuthentication(true)
                    //.addLogoutHandler(signoutHandler)
                .and()
                .csrf()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                ;
    }
    
    @Bean
    public AuthenticationPrincipalArgumentResolver webSecurityExpressionHandler(){
        return new AuthenticationPrincipalArgumentResolver(); 
    }
    
    @Bean
    public DefaultWebSecurityExpressionHandler handler(){
        return new DefaultWebSecurityExpressionHandler(); 
    }
}
