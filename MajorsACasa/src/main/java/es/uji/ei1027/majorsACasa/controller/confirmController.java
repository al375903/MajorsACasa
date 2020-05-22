package es.uji.ei1027.majorsACasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class confirmController {

    @RequestMapping("/confirm")
    public String confirm(Model model) {
        model.addAttribute("accion", new String());
        return "confirm";
    }

    @RequestMapping(value="/confirm", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("accion") String accion,          
                BindingResult bindingResult, HttpSession session) {
        
        session.setAttribute("accion", accion);
        String nextUrl = (String) session.getAttribute("nextUrl");
        return "redirect:"+nextUrl;
    }
}
