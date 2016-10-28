package com.dareu.web.service.impl;

import com.dareu.web.service.AdminService;
import com.dareu.web.service.ApacheConnectorService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
public class AdminServiceImpl implements AdminService{
    
    private Logger log = Logger.getLogger("AdminService"); 
    
    @Autowired
    private ApacheConnectorService service; 
    
    public AdminServiceImpl(){
        
    }

    @Override
    public ModelAndView defaultView() {
        return new ModelAndView("admin/index"); 
    }

    @Override
    public ModelAndView configurationView() {
        return new ModelAndView("admin/configuration"); 
    }

    @Override
    public ModelAndView usersView() {
        return new ModelAndView("admin/users"); 
    }

    @Override
    public ModelAndView daresView() {
        return new ModelAndView("admin/dares"); 
    }
    
}
