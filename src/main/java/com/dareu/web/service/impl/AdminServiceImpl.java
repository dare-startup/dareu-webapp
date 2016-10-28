package com.dareu.web.service.impl;

import com.dareu.data.entity.Category;
import com.dareu.data.exception.DataAccessException;
import com.dareu.data.repository.DareUserRepository;
import com.dareu.web.conn.DareOperation;
import com.dareu.web.security.DareuUserDetails;
import com.dareu.web.service.AdminService;
import com.dareu.web.service.ApacheConnectorService;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jose.rubalcaba
 */
@Component
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
    public ModelAndView configurationView(DareuUserDetails details) {
        
        if(details == null)
            log.info("User details is null");
        //creates a view model 
        ModelAndView mav = new ModelAndView("admin/configuration"); 
        
        //get categories
        Type type = new TypeToken<List<Category>>(){}.getType(); 
        try{
            //.... get user by email using super admin operation 
            throw new IOException(); 
        } catch(IOException ex){
            log.severe("Could not connect to DareU api: " + ex.getMessage());
        }
        return mav;
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
