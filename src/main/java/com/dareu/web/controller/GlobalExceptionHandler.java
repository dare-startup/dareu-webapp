package com.dareu.web.controller;

import com.dareu.web.controller.user.MemberController;
import com.dareu.web.controller.user.MemberDareController;
import com.dareu.web.controller.user.MemberDiscoverController;
import com.dareu.web.exception.DareuWebApplicationException;
import com.dareu.web.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@EnableWebMvc
@ControllerAdvice(basePackageClasses = { 
    //default 
    DefaultController.class,
    //member
    MemberController.class, 
    MemberDareController.class, 
    MemberDiscoverController.class })
public class GlobalExceptionHandler {
    
    @Autowired
    private MemberService memberService; 
    
    @ExceptionHandler(DareuWebApplicationException.class)
    public String handleException(DareuWebApplicationException ex, HttpServletRequest req){
        return memberService.handleException(ex, req); 
    }
    
    
}
