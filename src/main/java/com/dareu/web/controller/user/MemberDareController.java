package com.dareu.web.controller.user;

import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.dto.request.DareConfirmationRequest;
import com.dareu.web.exception.DareuWebApplicationException;
import com.dareu.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jose.rubalcaba
 */
@Controller
@RequestMapping("member/dare")
public class MemberDareController {
    
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value="create")
    public ModelAndView createDareView(Model model, RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.createDareView(model, atts); 
    }
    
    @RequestMapping(value="create", method = RequestMethod.POST)
    public String createDare(@ModelAttribute("dare") CreateDareRequest request, RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.createDare(request, atts);
    }
    
    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public String confirmDareRequest(@ModelAttribute("dareConfirmation")DareConfirmationRequest confirmationRequest, 
                                    RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.confirmDareRequest(confirmationRequest, atts);
    }
    
    @RequestMapping(value = "active")
    public ModelAndView currentActiveDare(RedirectAttributes atts)throws DareuWebApplicationException{
        return memberService.currentActiveDare(atts);
    }
     
    @RequestMapping(value = "profile")
    public ModelAndView currentProfile(@ModelAttribute("message")String message)throws DareuWebApplicationException{
        return memberService.currentUserProfile(message); 
    }
    
    /**@RequestMapping(value = "response/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String uploadDareResponse(@RequestPart("file")MultipartFile file, @RequestParam("dareId") String dareId, @RequestParam("comment")String comment, RedirectAttributes atts)
    throws DareuWebApplicationException{
        return memberService.uploadDareResponse(file, comment, dareId, atts); 
    }**/
    
    @RequestMapping(value = "response/upload")
    public ModelAndView uploadDareResponseView(@ModelAttribute("dareId")String dareId)throws DareuWebApplicationException{
        return memberService.uploadDareResponseView(dareId); 
    }
}
