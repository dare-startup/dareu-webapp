package com.dareu.web.controller.user;

import com.dareu.web.dto.request.CreateDareRequest;
import com.dareu.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView createDareView(Model model,RedirectAttributes atts){
        return memberService.createDareView(model, atts); 
    }
    
    @RequestMapping(value="create", method = RequestMethod.POST)
    public String createDare(@ModelAttribute("dare") CreateDareRequest request, RedirectAttributes atts){
        return "redirect:/"; 
    }
}
