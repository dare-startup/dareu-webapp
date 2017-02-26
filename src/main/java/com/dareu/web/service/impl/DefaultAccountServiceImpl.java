package com.dareu.web.service.impl;

import com.dareu.web.conn.ApacheResponseWrapper;
import com.dareu.web.conn.PublicMethodName;
import com.dareu.web.dto.request.SignupRequest;
import com.dareu.web.dto.response.AuthenticationResponse;
import com.dareu.web.dto.security.PasswordEncryptor;
import com.dareu.web.conn.ApacheConnectorService;
import com.dareu.web.service.DefaultAccountService;
import com.dareu.web.conn.cxt.JsonParserService;
import com.dareu.web.exception.ApplicationError;
import com.dareu.web.exception.ApplicationError.ErrorCode;
import com.dareu.web.exception.DareuWebApplicationException;
import com.dareu.web.mgr.FileManager;
import com.dareu.web.service.AbstractService;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Component
public class DefaultAccountServiceImpl extends AbstractService implements DefaultAccountService{
    
    private static final Logger log = Logger.getLogger(DefaultAccountServiceImpl.class.getName()); 
    
    @Autowired
    private ApacheConnectorService connector; 
    
    @Autowired
    private JsonParserService jsonParser; 
    
    @Autowired
    private HttpServletRequest servletRequest;
    
    @Autowired
    private PasswordEncryptor encryptor;
    
    @Autowired
    private FileManager fileManager; 

    @Override
    public ModelAndView defaultView() {
        return new ModelAndView(getView(JspView.DEFAULT_INDEX)); 
    }

    @Override
    public ModelAndView signinView() {
        return new ModelAndView(getView(JspView.SIGNIN)); 
    }

    @Override
    public String signupView(Model model) {
        model.addAttribute("signup", new SignupRequest()); 
        return "signup"; 
    }
    
    @Override
    public String signupRequest(SignupRequest request, RedirectAttributes atts){
        final String signup = "redirect:signup"; 
        final String signin = "redirect:signin"; 
        //validate request
        if(request == null){
            atts.addFlashAttribute("error", "There has been an error, try again");
            return signup;
        }
        else if(request.getName() == null || request.getName().isEmpty()){
            atts.addFlashAttribute("error", "No name was provided"); 
            return signup;
        }else if(request.getEmail() == null || request.getEmail().isEmpty()){
            atts.addFlashAttribute("error", "No email was provided"); 
            return signup;
        }else if(request.getPassword() == null || request.getPassword().isEmpty()){
            atts.addFlashAttribute("error", "No password was provided"); 
            return signup;
        }else if(request.getBirthday() == null || request.getBirthday().isEmpty()){
            atts.addFlashAttribute("error", "No birthday was provided"); 
            return signup;
        }else{
            //connect to web service 
            try{
                AuthenticationResponse response = null; 
                ApacheResponseWrapper wrapper = connector
                        .performPublicPostOperation(PublicMethodName.SIGNUP.toString(), request);
                if(wrapper != null){
                    switch(wrapper.getStatusCode()){
                        case 200: 
                            log.info(String.format("User %s has been created successfully", request.getEmail()));
                            //the user has been created successfully 
                            response = jsonParser.<AuthenticationResponse>parseJson(wrapper.getJsonResponse(), 
                                    AuthenticationResponse.class);
                            //save token 
                            servletRequest.getSession(true)
                                    .setAttribute("userToken", response.getToken());
                            return signin;
                        case 500:
                            log.severe("DareServices returned status code 500 registering user");
                            atts.addFlashAttribute("error", "Something went wrong, try again"); 
                            return signup;
                        case 404: 
                            log.severe("Could not find services");
                            atts.addFlashAttribute("error","Could not find services");
                            return signup;
                    }
                }else{
                    atts.addFlashAttribute("error", "Could not get response from dareu server, try again"); 
                    return signup;
                }
            }catch(IOException ex){
                log.severe(String.format("IOException: %s", ex.getMessage()));
                atts.addFlashAttribute("error", "Could not get response from dareu server, try again"); 
                return signup; 
            }
        }return signin;
    }
    
    
    @Override 
    public void downloadAndroidMobileApplication(HttpServletResponse response)throws DareuWebApplicationException{
        //get file 
        try{
            InputStream stream = fileManager.getAndroidApkFile(); 
            
            response.setContentType("application/octet-stream");
            /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
            response.setHeader("Content-Disposition", String.format("inline; filename=\"dareu.apk\""));
            byte[] buffer = new byte[1024]; 
            while ((stream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer);
            }
            stream.close();
            response.getOutputStream().close();
        }catch(IOException ex){
            throw new DareuWebApplicationException(new ApplicationError(ErrorCode.IO_ERROR, ex.getMessage(), null)); 
        }
    }
}
