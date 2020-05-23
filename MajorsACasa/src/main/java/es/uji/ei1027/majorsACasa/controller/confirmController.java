package es.uji.ei1027.majorsACasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.model.Accion;

@Controller
public class confirmController {

    @RequestMapping("/confirm")
    public String confirm(Model model) {
        model.addAttribute("accion", new String());
        return "confirm";
    }

    @RequestMapping(value="/confirm", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("accion") Accion accion,          
                BindingResult bindingResult, HttpSession session) {
        
    	String nextUrl = "/";
        if(session.getAttribute("accion") != null) { //En este if accion = nextUrl = delete/id
        	nextUrl = (String) session.getAttribute("accion");
        	session.removeAttribute("accion");
        }
        session.setAttribute("confirmar", accion);
        return "redirect:"+nextUrl;
    }
}
