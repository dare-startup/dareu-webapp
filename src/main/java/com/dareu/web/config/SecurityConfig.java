package com.dareu.web.config;

import com.dareu.web.dto.security.SecurityRole;
import com.dareu.web.security.DareuAuthenticationProvider;
import com.dareu.web.security.DareuUserDetailsService;
import com.dareu.web.security.handler.DareuAccessDeniedHandler;
import com.dareu.web.security.handler.DareuAuthenticationFailedHandler;
import com.dareu.web.security.handler.DareuAuthenticationSuccessHandler;
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
    public void configureGlobalSecurity(AuthenticationManagerBuilder builder)throws Exception{
        builder.userDetailsService(service);
        builder.authenticationProvider(provider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signin", "signup", "/", "/about", "/contact").permitAll()
                
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
                .csrf()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                
                ;
    }
    /**@Configuration
    @Order(1)
    public class UserHttpSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/member/**").hasRole(SecurityRole.USER.toString())
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
                    .accessDeniedPage("/error/unauthorized");
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
                    .accessDeniedPage("/error/unauthorized");
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
                    .accessDeniedPage("/error/unauthorized");
        }
    }**/
    
    @Bean
    public AuthenticationPrincipalArgumentResolver webSecurityExpressionHandler(){
        return new AuthenticationPrincipalArgumentResolver(); 
    }
    
    @Bean
    public DefaultWebSecurityExpressionHandler handler(){
        return new DefaultWebSecurityExpressionHandler(); 
    }
}
